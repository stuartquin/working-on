(ns clojure-rest.test.handler
  (:use clojure.test
        ring.mock.request
        midje.sweet
        clojure-rest.handler)
  (:require [clojure.test :as test]
            [monger.collection :as mc]))

(fact "GET /entries request returns results with midje"
      (prerequisite (connect-db) => true
                    (retreive-sorted-entries) => ["test"] :times 1)
      (:body (app (request :get "/entries"))) => "{\"results\":[\"test\"]}"
      (:status (app (request :get "/entries"))) => 200)
        
(fact "POST /entries saves value"
      (prerequisite (connect-db) => true
                    (mc/insert-and-return "entries" anything) => "test")
      (let [req (request :post "/entries" "{\"text\":\"test\"}")]
        (:body (app (content-type req "application/json"))) => "test"))
