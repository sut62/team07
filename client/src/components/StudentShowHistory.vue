<template> 

    <v-temp class="cyan lighten-5"> 

  <v-container class="cyan darken-1" >
    
    <v-layout text-center wrap>
    <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Information</h1>
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

<script >


 
export default {
  name: "studentHistory",
  data() {
    return {
      headers: [
        {
          text: "รหัสนักศึกษา",
          align: "left",
          sortable: false,
          value: "student_id"
        },
        { text: "คำนำหน้า", value: "prefix.name" },
        { text: "่ชื่อ", value: "student_name" },
        { text: "สาขาวิชา", value: "major.name" },
        { text: "ชั้นปีการศึกษา", value: "year.year_name" },
        { text: "Email", value: "student_email" },
        { text: "เบอร์โทรศัพท์", value: "student_phone" }
       // { text: "Password", value: "password" },

        
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
