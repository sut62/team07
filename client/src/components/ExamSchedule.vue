<template>
    <v-container>
        <v-form ref="form" v-model="valid" persistent max-width="500px">
            <v-layout text-center wrap>
                <v-container>
                    <div>
                        <p class="font-weight-black display-1">Exam schedule</p>
                    </div>
                    <v-row justify="center">
                         <v-col class="d-flex" cols="12" sm="3">
                             <v-select
                                     label="ภาคการศึกษา"
                                     outlined
                                     v-model="examSchedule.semesterId"
                                     :items="semester"
                                     item-text="sem"
                                     item-value="id"
                                     :rules="[(v) => !!v || 'กรุณาเลือกภาคการศึกษา']"
                                     required
                             ></v-select>
                         </v-col>
                        <v-col cols="12" sm="3">
                            <v-text-field
                                    v-model="examSchedule.academicYear"
                                    label="ปีการศึกษา"
                                    outlined
                                    clearable
                                    :rules="[(v) => !!v || 'กรุณาใส่ปีการศึกษา']"
                                    required
                            ></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col class="d-flex" cols="12" sm="3">
                            <v-select
                                    label="วิชา"
                                    outlined
                                    v-model="examSchedule.courseId"
                                    :items="course"
                                    item-text="name"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'กรุณาเลือกวิชา']"
                                    required
                            ></v-select>
                        </v-col>
                        <v-col class="d-flex" cols="12" sm="3">
                            <v-select
                                    label="ห้องสอบ"
                                    outlined
                                    v-model="examSchedule.roomId"
                                    :items="room"
                                    item-text="name"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'กรุณาเลือกห้องสอบ']"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="12" sm="2" md="2">
                            <v-menu
                                    v-model="menu1"
                                    :close-on-content-click="false"
                                    :nudge-right="40"
                                    transition="scale-transition"
                                    offset-y
                                    full-width
                                    min-width="250px"
                            >
                                <template v-slot:activator="{ on }">
                                    <v-text-field
                                            v-model="examSchedule.date"
                                            label="วันที่สอบ"
                                            readonly
                                            v-on="on"
                                    ></v-text-field>
                                </template>
                                <v-date-picker v-model="examSchedule.date" @input="menu1 = false">
                                </v-date-picker>
                            </v-menu>
                        </v-col>
                        <v-col cols="12" sm="2">
                             <v-menu
                                ref="menu2"
                                v-model="menu2"
                                :close-on-content-click="false"
                                :nudge-right="40"
                                :return-value.sync="time"
                                transition="scale-transition"
                                offset-y
                                max-width="200px"
                                min-width="200px"
                             >
                             <template v-slot:activator="{ on }">
                                  <v-text-field
                                    v-model="examSchedule.startTime"
                                    label="เวลาเริ่มสอบ"
                                    readonly
                                    v-on="on"
                                  ></v-text-field>
                             </template>
                             <v-time-picker
                                  v-if="menu2"
                                  v-model="examSchedule.startTime"
                                  full-width
                                  @click:minute="$refs.menu2.save(examSchedule.startTime)"
                             ></v-time-picker>
                             </v-menu>
                        </v-col>
                        <v-col cols="12" sm="2">
                             <v-menu
                                ref="menu3"
                                v-model="menu3"
                                :close-on-content-click="false"
                                :nudge-right="40"
                                :return-value.sync="time"
                                transition="scale-transition"
                                offset-y
                                max-width="200px"
                                min-width="200px"
                             >
                             <template v-slot:activator="{ on }">
                                  <v-text-field
                                    v-model="examSchedule.endTime"
                                    label="เวลาสิ้นสุดสอบ"
                                    readonly
                                    v-on="on"
                                  ></v-text-field>
                             </template>
                             <v-time-picker
                                  v-if="menu3"
                                  v-model="examSchedule.endTime"
                                  full-width
                                  @click:minute="$refs.menu3.save(examSchedule.endTime)"
                             ></v-time-picker>
                             </v-menu>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="12" sm="6">
                            <v-textarea
                                    v-model="examSchedule.annotation"
                                    label="หมายเหตุ"
                                    auto-grow
                                    outlined
                                    rows="1"
                                    row-height="15"
                                    clearable
                                    :rules="[(v) => !!v || 'กรุณาใส่หมายเหตุ 5-200 ตัวอักษร']"
                                    required
                            ></v-textarea>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="12">
                            <v-btn :disabled="!valid" color="success" @click="saveExamSchedule">Save</v-btn>
                            <v-snackbar v-model="snackbar">{{text}}</v-snackbar>
                            <v-btn style="margin-left: 15px;" @click="clear">Reset</v-btn>
                        </v-col>
                    </v-row>
                </v-container>
            </v-layout>
        </v-form>
    </v-container>
</template>


<script>
/* eslint-disable */
export default {
  name: "examSchedule",
  data() {
    return {
        examSchedule: {
        semesterId: [],
        courseId: [],
        roomId: []
      },
      snackbar: false,
      valid: false,
      menu1: false,
      menu2: false,
      menu3: false,
      semester: null,
      academicYear: null,
      course: null,
      room: null,
      date: new Date().toISOString().substr(0, 10),
      time: null,
      startTime: null,
      endTime: null,
      annotation: null
    };
  },

  methods: {
  /* eslint-disable no-console */

    getSemesters() {
      this.$http
        .get("/semester")
        .then(response => {
          this.semester = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getCourses() {
      this.$http
        .get("/courses")
        .then(response => {
          this.course = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

      getRooms() {
          this.$http
              .get("/room")
              .then(response => {
                  this.room = response.data;
                  console.log(response.data);
              })
              .catch(e => {
                  console.log(e);
              });
      },

      saveExamSchedule() {
          this.$http
              .post(
                  "/examSchedule/" +
                  this.examSchedule.semesterId +
                  "/" +
                  this.examSchedule.academicYear +
                  "/" +
                  this.examSchedule.courseId +
                  "/" +
                  this.examSchedule.roomId +
                  "/" +
                  this.examSchedule.date +
                  "/" +
                  this.examSchedule.startTime +
                  "/" +
                  this.examSchedule.endTime +
                  "/" +
                  this.examSchedule.annotation
              )
              .then(response => {
                  console.log(response);
                  this.text = 'บันทึกข้อมูลสำเร็จ';
                  this.snackbar = true;
                  this.$refs.form.reset();
              })
              .catch(e => {
                  console.log(e);
                  this.text = 'บันทึกข้อมูลไม่สำเร็จ';
                  this.snackbar = true;
              });

      },

    clear() {
      this.$refs.form.reset();
    },

    /* eslint-enable no-console */
  },

  mounted() {
      this.getSemesters();
      this.getCourses();
      this.getRooms();
  }
};
</script>
