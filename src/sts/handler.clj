(ns sts.handler
  (:require [sts.query :as query]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.core :refer [GET POST defroutes]]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defroutes app-routes
  (GET "/tax/:id" [id]
       (response (query/get-tax id)))
  (GET "/tax/zip/:zip" [zip]
       (response (query/get-tax-by-zip zip)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)))
