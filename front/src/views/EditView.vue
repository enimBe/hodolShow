<script setup lang="ts">
import {ref} from "vue";

import axios from "axios";
import {useRouter} from "vue-router";
import {defineProps} from 'vue/dist/vue';

const router = useRouter();

const props = defineProps({
  postId: {
    type: [Number, String],
    require: true,
  },
});

const post = ref({
  id: 0,
  title: "",
  content: "",
});

axios.get(`/api/posts/${props.postId}`).then((response) => {
  post.value = response.data;
});

const edit = () => {
  axios.patch(`/api/posts/${props.postId}`, post.value).then((response) => {
    router.replace({name: "home"})
  });

}
</script>

<template>
  <div>
    <el-input v-model="post.title"/>
  </div>

  <div class="mt-2">
    <el-input v-model="post.content" type="textarea" rows="15"/>
  </div>

  <div class="mt-2 d-flex justify-content-end">
    <el-button type="warning" @click="edit()">submit</el-button>
  </div>

</template>

<style>

</style>