<template>
<v-content>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ผลการลงทะเบียน</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="10">
        <p></p>
        <v-data-table :headers="headers" :items="items" :items-per-page="10" class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</v-content>
</template>

<script>

export default {
  name: "ViewRegister",
  data() {
    return {
      search: '',
      headers: [
        {
          text: "วันที่-เวลา",
          filterable: false,
          value: "registerDate"},
        {
          text: "ชื่อ-สกุล",
          align: "left",
          sortable: false,
          value: "registerBy.student_name"
        },
        { text: "ภาคการศึกษา/ปีการศึกษา", value: "inSemester.sem" },
        { text: "รหัสวิชา", value: "course" },
        { text: "รายวิชา", value: "chooseSec.subInSec.name" },
        { text: "กลุ่มเรียน", value: "chooseSec.sec" },
        { text: "หน่วยกิต", value: "credit" }
      ],
      items: []
    };
  },
  methods: {
    // ดึงข้อมูล register ใส่ combobox
    getRegisters() {
      this.$http
        .get("/register")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getRegisters();
    }
  },
  mounted() {
    this.getRegisters();
  }
};
</script>
