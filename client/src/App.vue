<template >
  <v-app>
      <router-view />
  </v-app>
</template>

<script>
export default {
  created() {
    const username = localStorage.getItem("username")
    if (username !== null) {
      this.$store.commit("setUsername", username)
    }
  }
};
</script>
