(ns clojure-rest.test.handler
  (:use clojure.test
        ring.mock.request  
        clojure-rest.handler))

(deftest test-app
  (testing "Entries path"
    (let [response (app (request :get "/entries"))]
      (is (= (:status response) 200)))))
