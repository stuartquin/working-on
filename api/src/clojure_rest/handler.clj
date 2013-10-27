(ns clojure-rest.handler
    (:import [com.mongodb DB WriteConcern]
             [org.bson.types ObjectId]
             [java.util.Date])
    (:require [compojure.core :refer [defroutes context GET POST]]
              [ring.util.response :refer [response]]
              [compojure.handler :as handler]
              [clj-time.core]
              [monger.core :as mg]
              [monger.query :as mq]
              [monger.json]
              [monger.joda-time]
              [monger.collection :as mc]
              [ring.middleware.json :as middleware]
              [ring.middleware.cors :refer [wrap-cors]]
              [compojure.route :as route]))

  (defn connect-db []
    (mg/connect!)
    (mg/set-db! (mg/get-db "monger-test")))

  (defn retreive-sorted-entries []
    (mq/with-collection "entries"
      (mq/find {})
      (mq/fields [:created_at :text :_id :tags])
      (mq/sort (array-map :_id -1))
      (mq/limit 10)))

  (defn get-all-entries []
    (connect-db)
    (response {:results (retreive-sorted-entries)}))

  (defn create-new-entry [entry]
    (connect-db)
    (let [db_entry (merge {"created_at" (clj-time.core/now)} entry)]
      (response (mc/insert-and-return "entries" db_entry))))

  (defroutes app-routes
    (context "/entries" [] (defroutes entries-routes
      (GET  "/" [] (get-all-entries))
      (POST "/" {body :body} (create-new-entry body))))
    (route/not-found "Not Found"))

  (def app
      (-> (handler/api app-routes)
          (middleware/wrap-json-body)
          (wrap-cors :access-control-allow-origin #".*")
          (middleware/wrap-json-response)))
