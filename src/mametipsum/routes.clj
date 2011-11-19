(ns mametipsum.routes
  (:require
   [compojure.route :as route]
   [mametipsum.views :as views])
  (:use
   [compojure.core]))

(defroutes main-routes
  (GET "/" [] (views/home))
  (route/resources "/")
  (route/not-found "404"))
