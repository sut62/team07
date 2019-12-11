<template>
  <v-dialog v-model="dialog" persistent class="mx-auto">
    <template v-slot:activator="{ on }">
      <v-btn color="primary" v-on="on" width="100%">เลือกรายวิชาที่สอน</v-btn>
    </template>
    <v-card raised>
      <v-toolbar color="primary">
        <v-btn icon @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>รายวิชาที่สอน</v-toolbar-title>
      </v-toolbar>
      <v-card-title></v-card-title>
      <v-card-text>
        <v-text-field
          v-model="search"
          append-icon="mdi-card-search-outline"
          label="ค้นหารายวิชา"
          single-line
        ></v-text-field>
        <v-data-table :headers="headers" :items="courses" :search="search" show-select></v-data-table>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn text color="primary">เลือก</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapState } from "vuex";

export default {
  data: () => ({
    dialog: false,
    search: "",
    headers: [
      {
        text: "รหัสวิชา",
        value: "courseCode",
        sortable: true
      },
      {
        text: "ชื่อวิชา",
        value: "name"
      },
      {
        text: "หน่วยกิต",
        value: "weight"
      },
      {
        text: "สำนักวิชา",
        value: "major.institute.name"
      },
      {
        text: "สาขาวิชา",
        value: "major.name"
      }
    ]
  }),
  created() {
    this.$store.commit("setCourses");
  },
  computed: {
    ...mapState({
      courses: state => state.courses
    })
  }
};
</script>