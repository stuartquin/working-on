(ns clojure-rest.test.handler
  (:use clojure.test
        ring.mock.request  
        clojure-rest.handler
        clojure.contrib.core
        clojure.contrib.mock.test-adapter)
  (:require [clojure.test :as test]))


(deftest test-app
  (testing "Entries path"
    (expect [mc/find-maps (times once (has-args "entries"))]
      (let [response (app (request :get "/entries"))]
        (is (= (:status response) 200))))))
