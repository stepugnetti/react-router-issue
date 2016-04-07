(ns router-issue.core
  (:require [reagent.core :as r]
            [cljsjs.react-router]))

(enable-console-print!)

(def react-router js/ReactRouter)

(def router (r/adapt-react-class (.-Router react-router)))
;; (def router* (r/adapt-react-class (.-Router react-router)))
;; (defn router
;;   [& args]
;;   (into [route*] args))

(def route (r/adapt-react-class (.-Route react-router)))
;; NEITHER OF THESE WORKS.
;; (def route* (r/adapt-react-class (.-Route react-router)))
;; (defn route
;;   [opts & children]
;;   [route* opts children])
;; (defn route
;;   [& args]
;;   (into [route*] args))

;; THIS WORKS IN BOTH WAYS.
;; (def link (r/adapt-react-class (.-Link react-router)))
(def link* (r/adapt-react-class (.-Link react-router)))
(defn link
  [& args]
  (into [link*] args))

(def index-route (r/adapt-react-class (.-IndexRoute react-router)))
;; (def index-route* (r/adapt-react-class (.-IndexRoute react-router)))
;; (defn index-route
;;   [& args]
;;   (into [index-route*] args))

(defn app
  []
  [router
   [route {:path "/"}
    [index-route {:component (r/reactify-component
                               (fn []
                                 [:p "From here you can either "
                                  [link {:to "login"} "log in"]
                                  " or "
                                  [link {:to "logout"} "log out"]]))}]
    [route {:path "login"
            :component (r/reactify-component
                         (fn []
                           [:div
                            [:p "Here you log in."]
                            [link {:to "logout"} "Log out"]]))}]
    [route {:path "logout"
            :component (r/reactify-component
                         (fn []
                           [:div
                            [:p "Here you log out."]
                            [link {:to "login"} "Log in"]]))}]]])

(r/render-component
  app (js/document.getElementById "app"))
