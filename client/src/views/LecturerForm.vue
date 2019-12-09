<template>
  <v-card class="mx-auto yellow lighten-4" raised width="50%">
    <v-card-title>เพิ่มอาจารย์ผู้สอน</v-card-title>
    <v-card-text>
      <v-form>
        <v-text-field label="ชื่อ - สกุล"></v-text-field>
        <v-text-field label="รหัสอาจารย์"></v-text-field>
        <v-text-field label="รหัสผ่าน" type="password"></v-text-field>
        <v-radio-group label="เพศ" row>
          <v-radio label="ชาย" color="primary"></v-radio>
          <v-radio label="หญิง" color="primary"></v-radio>
        </v-radio-group>
        <v-text-field label="เบอร์โทรศัพท์" type="tel"></v-text-field>
        <v-text-field label="E-mail" type="email"></v-text-field>
        <v-combobox label="สำนักวิชา"></v-combobox>
        <v-combobox label="สาขาวิชา"></v-combobox>
        <subject-list-table></subject-list-table>
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="primary" text>เพิ่มอาจารย์ผู้สอน</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import SubjectListTable from "@/components/SubjectListTable";

export default {
  components: {
    SubjectListTable
  },
  data: () => ({
    table: false
  })
};
</script>