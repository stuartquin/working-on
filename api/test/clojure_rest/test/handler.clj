(ns clojure-rest.test.handler
  (:use clojure.test
        ring.mock.request
        midje.sweet
        clojure-rest.handler)
  (:require [clojure.test :as test]))

(fact "GET /entries request returns results with midje"
      (prerequisite (connect-db) => true
                    (retreive-sorted-entries) => ["test"] :times 1)
      (:body (app (request :get "/entries"))) => "{\"results\":[\"test\"]}"
      (:status (app (request :get "/entries"))) => 200)
        

;(fact "POST /entries saves value"
;      (:body (app (request :get "/entries"))) => "{\"results\":[\"test\"]}"
;      (provided
;        (retreive-sorted-entries) => ["test"] :times 1
;        (connect-db) => true))
