(ns clojure-rest.test.handler
  (:use clojure.test
        ring.mock.request
        midje.sweet
        clojure-rest.handler)
  (:require [clojure.test :as test]))

(with-redefs [retreive-sorted-entries (fn [] ["test"])
              connect-db (fn [] true)]
  (fact (:status (app (request :get "/entries"))) => 200
        (:body (app (request :get "/entries"))) => "{\"results\":[\"test\"]}"))
