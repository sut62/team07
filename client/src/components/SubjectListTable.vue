<template>
  <v-dialog v-model="dialog" persistent class="mx-auto">
    <template v-slot:activator="{ on }">
      <v-btn color="primary" v-on="on" expanded>รายวิชาที่เปิดสอน</v-btn>
    </template>
    <v-card raised>
      <v-toolbar color="primary">
        <v-btn icon @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>รายวิชา</v-toolbar-title>
      </v-toolbar>
      <v-card-title></v-card-title>
      <v-card-text>
        <v-text-field
          v-model="search"
          append-icon="mdi-card-search-outline"
          label="ค้นหารายวิชา"
          single-line
        ></v-text-field>
        <v-data-table :headers="headers" :items="courses" :search="search" show-select v-model="$v.selected.$model" single-select></v-data-table>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn text color="primary" @click="selectedCourses" :disabled="$v.selected.$invalid">เลือก</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapState } from "vuex";
import { required } from 'vuelidate/lib/validators'

export default {
  data: () => ({
    dialog: false,
    selected: [],
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
        value: "credit"
      },
      {text: "หลักสูตร",value: "programInfo.name"},
      {text: "ประเภทวิชา",value: "type.name"},
    ]
  }),
  validations: {
    selected: {
      required
    }
  },
  created() {
    this.$store.commit("setCourses");
  },
  computed: {
    ...mapState({
      courses: state => state.courses
    })
  },
  methods: {
    selectedCourses: async function() {
      this.$emit('selectedCourses', this.$v.selected.$model)
      this.dialog = false
    }
  }
};
</script>