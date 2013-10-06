(ns clojure-rest.handler
      (:import [com.mongodb DB WriteConcern]
               [org.bson.types ObjectId]
               [java.util.Date])
      (:require [compojure.core :refer [defroutes context GET POST]]
                [ring.util.response :refer [response]]
                [compojure.handler :as handler]
                [clj-time.core]
                [monger.core :as mg]
                [monger.json]
                [monger.joda-time]
                [monger.collection :as mc]
                [ring.middleware.json :as middleware]
                [clojure.java.jdbc :as sql]
                [compojure.route :as route]))

    (mg/connect!)
    (mg/set-db! (mg/get-db "monger-test"))

    (defn get-all-entries []
      (response {:results (mc/find-maps "entries")}))

    (defn create-new-entry [doc]
      (let [existing (mc/find-maps "entries" {:title (doc "title")})
            db_doc (merge {"created_at" (clj-time.core/now)} doc)]
        (cond
          (empty? existing) (response (mc/insert-and-return "entries" db_doc))
          :else {:status 404})))
      

    ; (defn get-document [id]
    ;   (sql/with-connection (db-connection)
    ;     (sql/with-query-results results
    ;       ["select * from documents where id = ?" id]
    ;       (cond
    ;         (empty? results) 
    ;         :else (response (first results))))))

    ; (defn update-document [id doc]
    ;     (sql/with-connection (db-connection)
    ;       (let [document (assoc doc "id" id)]
    ;         (sql/update-values :documents ["id=?" id] document)))
    ;     (get-document id))

    ; (defn delete-document [id]
    ;   (sql/with-connection (db-connection)
    ;     (sql/delete-rows :documents ["id=?" id]))
    ;   {:status 204})

    (defroutes app-routes
      (context "/entries" [] (defroutes entries-routes
        (GET  "/" [] (get-all-entries))
        (POST "/" {body :body} (create-new-entry body))))
    ;    (context "/:id" [id] (defroutes document-routes
    ;      (GET    "/" [] (get-document id))
    ;      (PUT    "/" {body :body} (update-document id body))
    ;      (DELETE "/" [] (delete-document id))))))
      (route/not-found "Not Found"))

    (def app
        (-> (handler/api app-routes)
            (middleware/wrap-json-body)
            (middleware/wrap-json-response)))
