<template>
  <v-card class="mx-auto cyan lighten-4" raised>
    <v-card-title>
      รายชื่ออาจารย์
      <v-spacer></v-spacer>
      <v-text-field
        single-line
        hide-details
        v-model="search"
        label="ค้นหาอาจารย์"
        append-icon="mdi-account-search"
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="lecturers"
      no-results-text="ไม่พบอาจารย์ผู้สอน"
      no-data-text="ไม่มีข้อมูลอาจารย์ผู้สอน"
      :search="search"
      v-show="lecturers"
    ></v-data-table>
  </v-card>
</template>

<script>
import { mapState } from "vuex";

export default {
  data: () => ({
    headers: [
      { text: "รหัสอาจารย์", value: "lecturerCode" },
      { text: "คำนำหน้า", value: "prefix.name" },
      { text: "ชื่อ - สกุล", value: "name" },
      { text: "email", value: "email" },
      { text: "เบอร์โทรศัพท์", value: "tel" },
      { text: "เพศ", value: "gender.name" },
      { text: "สำนักวิชา", value: "major.institute.name" },
      { text: "สาขาวิชา", value: "major.name" }
    ],
    search: null
  }),
  computed: {
    ...mapState({
      lecturers: state => state.lecturers
    })
  },
  created() {
    this.$store.commit("setLecturers");
  }
};
</script>