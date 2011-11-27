(ns mametipsum.routes
  (:require
   [compojure.route :as route]
   [mametipsum.views :as views])
  (:use
   [compojure.core]))

(defroutes main-routes
  (GET "/" []
       (views/home))
  (GET "/mametipsum" []
       (views/list-scripts))
  (POST "/mametipsum/:script" [script]
        (views/create-script script))
  (GET "/mametipsum/:script/:nwords" [script nwords]
       (views/read-script script nwords))
  (PUT "/mametipsum/:script" [script]
       (views/update-script script))
  (DELETE "/mametipsum/:script" [script]
          (views/delete-script script))
  (route/resources "/")
  (route/not-found "404"))
