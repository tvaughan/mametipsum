(ns mametipsum.middleware
  (:use
   [ring.middleware.format-params :only (wrap-restful-params)]
   [ring.middleware.format-response :only (wrap-restful-response)]))

;; http://stackoverflow.com/a/7730478/162963
(defn- wrap-dir-index [handler]
  (fn [req]
    (handler
     (update-in req [:uri]
                #(if (= "/" %) "/index.html" %)))))

(defn api [handler]
  (wrap-restful-params (wrap-restful-response (wrap-dir-index handler))))
