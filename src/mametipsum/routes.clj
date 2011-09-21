(ns mametipsum.routes
  (:use
   [compojure.core]
   [mametipsum.views])
  (:require
   [compojure.route :as route]))

(defroutes mametipsum-routes
  (GET "/" [] (mametipsum-index-view))
  (route/resources "/")
  (route/not-found "404"))
