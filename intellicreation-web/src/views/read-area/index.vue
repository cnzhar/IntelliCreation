<template>
  <div>
    <div class="card" style="width: 18rem">
      <div class="card-header">Featured</div>
      <ul class="list-group list-group-flush">
        <li
          v-for="(item, index) in browseList"
          :key="'browseList' + index"
          class="list-group-item"
        >
          <a target="_blank">
            {{ item.title }}
          </a>
          —— {{ item.viewCount }} 次围观
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { hotArticleList } from "@/api/article";
export default {
  name: "read-index",
  data() {
    return {
      browseList: "", //热门文章 浏览量最多
    };
  },
  methods: {
    getHotArticleList() {
      hotArticleList().then((response) => {
        this.browseList = response.data;
        console.log(response);
        console.log(this.browseList);
      });
    },
  },
  created() {
    // 查询浏览量最多的10篇文章数据
    this.getHotArticleList();
  },
};
</script>

<style scoped></style>
