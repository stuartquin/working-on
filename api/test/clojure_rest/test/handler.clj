(ns clojure-rest.test.handler
  (:use clojure.test
        ring.mock.request
        midje.sweet
        clojure-rest.handler)
  (:require [clojure.test :as test]))

(fact (:status (app (request :get "/entries"))) => 200
      (:body (app (request :get "/entries"))) => "{\"results\":[\"test\"]}"
  (provided (retreive-sorted-entries) => ["test"] :times 1
            (connect-db) => [:whatever]))
