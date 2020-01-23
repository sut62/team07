<template>
  <v-card class="mx-auto cyan lighten-4" raised>
    <v-card-title>
      รายชื่อวิชา
      <v-spacer></v-spacer>
      <v-text-field
        single-line
        hide-details
        v-model="search"
        label="ค้นหารายวิชา"
        append-icon="mdi-account-search"
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="courses"
      no-results-text="ไม่พบรายวิชา"
      no-data-text="ไม่มีข้อมูลรายวิชา"
      :search="search"
      v-show="courses"
    ></v-data-table>
  </v-card>
</template>

<script>
import { mapState } from "vuex";

export default {
  data: () => ({
    headers: [
        {text: "รหัสวิชา",value: "courseCode"},
      {text: "ชื่อวิชา",value: "name"},
      {text: "หน่วยกิต",value: "credit"},
      {text: "หลักสูตร",value: "programInfo.name"},
      {text: "ประเภทวิชา",value: "type.name"},
      {text: "ภาคเรียน",value: "trimester.name"},
      {text: "อาจารย์ผู้สอน",value: "lecturer.name"},

    ],
    search: null
  }),
  computed: {
    ...mapState({
      courses: state => state.courses
    })
  },
  created() {
    this.$store.commit("setCourses");
  }
};
</script>