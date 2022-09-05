#!/usr/bin/env bb

(ns example.core
  (:gen-class)
  (:require
    [clojure.edn :as edn]
    [cognitect.transit :as transit]
    [hiccup.core :as hc]
    [fierycod.holy-lambda.response :as hr]
    [fierycod.holy-lambda.agent :as agent]
    [fierycod.holy-lambda.core :as h]))

(defn generate-template
  [event]
  #?(:bb 
     (println "GOOD TO GO")
     (->> [:h1 "SUBMITTED"]
            hc/html
            hr/html)))

(defn LambdaGenerateTemplate
  [{:keys [event ctx] :as request}]
  (generate-template event))

;; Specify the Lambda's entry point as a static main function when generating a class file
(h/entrypoint [#'LambdaGenerateTemplate])

(agent/in-context
 (println "I will help in generation of native-configurations"))
