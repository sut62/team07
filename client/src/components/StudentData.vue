
<template> 

    <v-temp class="cyan lighten-5" > 

  <v-container class="cyan lighten-10" >
    
    <v-layout text-center wrap>
    <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Members</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="20">
        <v-data-table :headers="headers" :items="items" :items-per-page="15" class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
   
  </v-temp>
 
</template>

<script>



export default {
  name: "ViewStudent",
  data() {
    return {
      headers: [
        {
          text: "Student_ID",
          align: "left",
          sortable: false,
          value: "student_id"
        },
        { text: "prefix", value: "prefix.name" },
        { text: "Name", value: "student_name" },
        { text: "Major", value: "major.name" },
        { text: "Year", value: "year.year_name" },
        { text: "Email", value: "student_email" },
        { text: "phone", value: "student_phone" },
        { text: "Password", value: "password" },

        
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล VideoRental ใส่ combobox
    getStudent() {
      this.$http
        .get("/student")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getStudent();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getStudent();
  }
};
</script>
