<template>
  <v-card class="mx-auto cyan lighten-4" raised>
    <v-card-title>
      ตารางเรียนนักศึกษา
      <v-spacer></v-spacer>
      <v-text-field
        single-line
        hide-details
        v-model="search"
        label="ค้นหาตารางเรียน"
        append-icon="mdi-account-search"
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      no-results-text="ไม่พบข้อมูล"
      no-data-text="ไม่มีข้อมูลตาราง"
      :search="search"
      :items="regisTable"
    ></v-data-table>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    headers: [
      {
        text: "ชื่อ-สกุล",
        align: "left",
        sortable: false,
        value: "registerBy.student_name"
      },
      { text: "ภาคการศึกษา/ปีการศึกษา", value: "inSemester.sem" },
      { text: "รหัสวิชา", value: "sub_num" },
      { text: "รายวิชา", value: "chooseSec.subInSec.name" },
      { text: "กลุ่มเรียน", value: "chooseSec.sec" },
      { text: "หน่วยกิต", value: "credit" },
      { text: "เวลาเรียน", value: "chooseSec.time" }
    ],
    search: null,
    regisTable: []
  }),
  methods: {
    async getRegisTable() {
      await this.$http.get("register").then(response => {
        this.regisTable = response.data;
      });
    }
  },
  async created() {
    await this.getRegisTable();
  }
};
</script>