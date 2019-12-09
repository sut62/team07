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
        <v-data-table :headers="headers"></v-data-table>
      </v-card-text>
      <v-card-actions></v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
    headers: [
      {
        text: "รหัสวิชา",
        value: "courseCode",
        sortable: true
      },
      {
        text: "ชื่อวิชา",
        value: "courseName"
      },
      {
        text: "หน่วยกิต",
        value: "weight"
      },
      {
        text: "สำนักวิชา",
        value: "institute"
      },
      {
        text: "สาขาวิชา",
        value: "major"
      }
    ]
  })
};
</script>