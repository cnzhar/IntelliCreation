import Vue from "vue";
import VueRouter from "vue-router";
import Layout from "@/views/layout/Layout";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "login",
    component: () => import("@/views/mine-area/Login"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/mine-area/Login"),
  },
  {
    path: "/home",
    component: Layout,
    redirect: "/home",
    name: "home",
    children: [
      {
        path: "",
        name: "home-index",
        component: () => import("@/views/Home/index"),
      },
    ],
  },
  {
    path: "/read",
    component: Layout,
    redirect: "/read/index",
    name: "read",
    children: [
      {
        path: "index",
        name: "read-index",
        component: () => import("@/views/read-area"),
      },
    ],
  },
  // {
  //   path: "/read/index",
  //   name: "read",
  //   component: () => import("@/views/read-area"),
  // },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
