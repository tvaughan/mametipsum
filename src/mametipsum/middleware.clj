(ns mametipsum.middleware
  (:use
   [ring.middleware.format-params :only (wrap-restful-params)]
   [ring.middleware.format-response :only (wrap-restful-response)]))

;; http://stackoverflow.com/a/7730478/162963
(defn- wrap-dir-index [handler]
  (fn [req]
    (handler
     ;; This will update `:path-info` and `:uri` if they both
     ;; exist. But what we should do is only update `:uri` when
     ;; `:path-info` does *not* exist.
     (update-in req (filter #(contains? req %) [:path-info :uri])
                #(if (re-find #"/$" %) (format "%sindex.html" %) %)))))

(defn api [handler]
  (wrap-restful-params (wrap-restful-response (wrap-dir-index handler))))
