(ns sts.handler
  (:require [sts.query :as query]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.core :refer [GET POST defroutes]]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defroutes app-routes
  (GET "/invoice" []
       (response (query/get-invoices)))
  (GET "/invoice/total" []
       (response (query/sum-invoices)))
  (GET "/invoice/:id" [id]
       (response (query/get-invoice id)))
  (POST "/invoice" request
        (let [number (or (get-in request [:params :number])
                         (get-in request [:body :number]))
              amount (or (get-in request [:params :amount])
                         (get-in request [:body :amount]))]
        (response (query/add-invoice number amount))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)))
