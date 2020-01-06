<template >

  <v-app >
    <div>
<v-app-bar app class ="deep-orange lighten-1" >
    
      <v-toolbar-title v-if="this.ids != null" class="headline text-uppercase">
        
        <span class="font-weight-light"> ระบบลงทะเบียนเรียน</span>
        &nbsp;
        <span>{{this.ids}}</span>
          
         <v-btn text href="/ " target="_self"></v-btn>
          <v-btn text href="/Register" target="_self">Register</v-btn>
           <v-btn text href="/ " target="_self"></v-btn>
            <v-btn text href="/ " target="_self"></v-btn>
            <v-btn @click="logOut" text target="_self">LOGOUT</v-btn>
        </v-toolbar-title>
        
   </v-app-bar>
    </div>
<v-content class="orange lighten-4">
     
      <router-view />
    </v-content>
  </v-app>
  
</template>

<script>
export default {
  name: 'App',
  data(){
    return{
      valid:false,
    check: false,
    ids: localStorage.getItem("siteUser")
    };
  },
  methods:{
    logOut(){
      localStorage.removeItem("siteId");
      localStorage.removeItem("siteUser");
      this.ids=null;
      this.$router.push("/");
},
  }
   };
</script>
