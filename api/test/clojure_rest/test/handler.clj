(ns clojure-rest.test.handler
  (:use clojure.test
        ring.mock.request
        midje.sweet
        clojure-rest.handler)
  (:require [clojure.test :as test]))

; (deftest test-app
;   (testing "Entries path"
;     (binding [retreive-sorted-entries #(str "test")]
;       (let [response (app (request :get "/entries"))]
;         (is (= (:body response) "{\"results\":\"test\"}"))
;         (is (= (:status response) 200))))))

(fact (:status (app (request :get "/entries"))) => 200
      (:body (app (request :get "/entries"))) => "{\"results\":\"test\"}"
  (provided (retreive-sorted-entries) => "test"))
