<template>
  <v-app>
    <v-app-bar app clipped-left color="primary">
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      <span class="title ml-3 mr-5">ระบบลงทะเบียนเรียน</span>
      <v-spacer />
    </v-app-bar>
    <v-navigation-drawer app v-model="drawer" clipped color="secondary">
      <!-- <v-list-item>
        <v-list-item-avatar>
          <v-img src="https://randomuser.me/api/portraits/men/78.jpg"/>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title>John Leider</v-list-item-title>
        </v-list-item-content>
      </v-list-item> -->

      <v-divider></v-divider>

      <v-list-item link to="register" color="black">
        <v-list-item-action>
            <v-icon>mdi-account-plus</v-icon>
          </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>ลงทะเบียนเรียน</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="viewreg" color="black">
        <v-list-item-action>
            <v-icon>mdi-account-plus</v-icon>
          </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>ผลการลงทะเบียนเรียน</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="tablereg" color="black">
        <v-list-item-action>
            <v-icon>mdi-account-plus</v-icon>
          </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>แสดงตารางเรียนเบียนเรียน</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="lecturer" color="black">
        <v-list-item-action>
            <v-icon>mdi-account-multiple</v-icon>
          </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>ค้นหาอาจารย์ผู้สอน</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="SearchTeachtable" color="black">
        <v-list-item-action>
          <v-icon>mdi-account-search</v-icon>
        </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>ค้นหาตารางสอนอาจารย์</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      

      <template v-slot:append>
        <div class="pa-2">
          <v-btn block color="error" @click="logout">Logout</v-btn>
        </div>
      </template>
    </v-navigation-drawer>
    <v-content>
      <v-container fluid class="cyan lighten-5 fill-height">
        <router-view />
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
export default {
  data: () => ({
    drawer: null
  }),
  methods: {
    logout: async function() {
      localStorage.removeItem("payload")
      this.$router.push("/login")
    }
  }
};
</script>