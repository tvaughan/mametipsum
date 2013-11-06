(ns mametipsum.middleware
  (:require [ring.middleware.format :refer [wrap-restful-format]]))

;; http://stackoverflow.com/a/7730478/162963
(defn- wrap-dir-index [handler]
  (fn [request]
    (handler
     (let [k (if (contains? request :path-info) :path-info :uri) v (get request k)]
       (if (re-find #"/$" v)
         (assoc request k (format "%sindex.html" v))
         request)))))

(defn api [handler]
  (wrap-restful-format (wrap-dir-index handler)))
