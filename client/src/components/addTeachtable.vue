<template>
    <v-card class="mx-auto yellow lighten-4" raised width="50%">
        <v-card-title>เพิ่มตารางสอนสำหรับอาจารย์</v-card-title>

                    <v-row justify="center">
                        <v-col class="d-flex" cols="9" >
                            <v-select
                                    label="ชื่ออาจารย์"
                                    outlined
                                    v-model="teachtable.lecturersId"
                                    :items="lecturers"
                                    item-text="name"
                                    item-value="id"
                                    :rules="[(v) => !!v || '*กรุณาเลือกชื่ออาจารย์']"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="9">
                            <v-text-field
                                    label="Email"
                                    outlined
                                    v-model="teachtable.email"
                                    clearable
                                    :rules="[(v) => !!v || '*กรุณากรอก Email และต้องมี @']"
                                    required
                            ></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col class="d-flex" cols="9">
                            <v-select
                                    label="วิชา"
                                    outlined
                                    v-model="teachtable.courseId"
                                    :items="course"
                                    item-text="name"
                                    item-value="id"
                                    :rules="[(v) => !!v || '*กรุณาใส่วิชา']"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col class="d-flex" cols="4">
                            <v-select
                                    label="ภาคการศึกษา"
                                    outlined
                                    v-model="teachtable.semesterId"
                                    :items="semester"
                                    item-text="sem"
                                    item-value="id"
                                    :rules="[(v) => !!v || '*กรุณาเลือกภาคการศึกษา']"
                                    required
                            ></v-select>
                        </v-col>
                        <v-col cols="5">
                            <v-text-field
                                    label="ปีการศึกษา"
                                    outlined
                                    v-model="teachtable.academicYear"
                                    clearable
                                    :rules="[(v) => !!v || '*กรุณาใส่ปีการศึกษาเป็นตัวเลข 4 ตัวและขึ้นต้นด้วยเลข 2']"
                                    required
                            ></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col class="d-flex" cols="5">
                            <v-select
                                    label="วัน"
                                    outlined
                                    v-model="teachtable.daysId"
                                    :items="days"
                                    item-text="name"
                                    item-value="id"
                                    :rules="[(v) => !!v || '*กรุณาเลือกวัน']"
                                    required
                            ></v-select>
                        </v-col>
                        <v-col class="d-flex" cols="4">
                            <v-select
                                    label="ห้องเรียน"
                                    outlined
                                    v-model="teachtable.roomId"
                                    :items="room"
                                    item-text="name"
                                    item-value="id"
                                    :rules="[(v) => !!v || '*กรุณาเลือกห้องเรียน']"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>

                    <v-row justify="center">
                        <v-col cols="4">
                            <v-menu
                                    ref="menu"
                                    v-model="start"
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
                                            v-model="teachtable.startTime"
                                            label="เวลาเริ่ม"
                                            readonly
                                            v-on="on"
                                    ></v-text-field>
                                </template>
                                <v-time-picker
                                        v-if="start"
                                        v-model="teachtable.startTime"
                                        :max="teachtable.endTime"
                                        full-width
                                        @click:minute="$refs.menu.save(teachtable.startTime)"
                                ></v-time-picker>
                            </v-menu>
                        </v-col>
                        <v-col cols="4">
                            <v-menu
                                    ref="menu"
                                    v-model="end"
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
                                            v-model="teachtable.endTime"
                                            label="เวลาสิ้นสุด"
                                            readonly
                                            v-on="on"
                                    ></v-text-field>
                                </template>
                                <v-time-picker
                                        v-if="end"
                                        v-model="teachtable.endTime"
                                        :min="teachtable.startTime"
                                        full-width
                                        @click:minute="$refs.menu.save(teachtable.endTime)"
                                ></v-time-picker>
                            </v-menu>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="9">
                            <v-text-field
                                    label="หมายเหตุ"
                                    outlined
                                    v-model="teachtable.annotation"
                                    clearable
                                    :rules="[(v) => !!v || '*กรุณากรอกหมายเหตุจำนวน 2-100 ตัวอักษร']"
                                    required
                            ></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="4">
                        <v-btn style="margin-left: 15px;" @click="saveTeachtable" color="success">save</v-btn>
                        <v-snackbar v-model="snackbar">{{text}}</v-snackbar>
                        <v-btn style="margin-left: 15px;" @click="clear" color="primary">clear</v-btn>
                        </v-col>
                    </v-row>
        </v-card>
</template>

<script>
    /* eslint-disable */
    export default {
        name: "teachtable",
        data() {
            return {
                teachtable: {
                    lecturersId: null,
                    courseId: null,
                    semesterId: null,
                    daysId: null,
                    roomId: null
                },
                snackbar: false,
                valid: false,
                text: null,
                start: null,
                end: null,
                lecturers: null,
                emails: null,
                course: null,
                semester: null,
                academicYear: null,
                days: null,
                room: null,
                time: null,
                startTime: null,
                endTime: null,
                annotation: null
            };
        },

        methods: {
            /* eslint-disable no-console */

            getLecturers() {
                this.$http
                    .get("/lecturers")
                    .then(response => {
                        this.lecturers = response.data;
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

            getDayss() {
                this.$http
                    .get("/days")
                    .then(response => {
                        this.days = response.data;
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

            saveTeachtable() {
                this.$http
                    .post(
                        "/teachtable/" +
                        this.teachtable.lecturersId +
                        "/" +
                        this.teachtable.email +
                        "/" +
                        this.teachtable.courseId +
                        "/" +
                        this.teachtable.semesterId +
                        "/" +
                        this.teachtable.academicYear +
                        "/" +
                        this.teachtable.daysId +
                        "/" +
                        this.teachtable.roomId +
                        "/" +
                        this.teachtable.startTime +
                        "/" +
                        this.teachtable.endTime +
                        "/" +
                        this.teachtable.annotation
                    )
                    .then(response => {
                        console.log(response);
                        this.text = 'บันทึกข้อมูลสำเร็จ';
                        this.snackbar = true;
                       // this.$refs.form.reset();
                    })
                    .catch(e => {
                        console.log(e);
                        this.text = 'บันทึกข้อมูลไม่สำเร็จ';
                        this.snackbar = true;
                    })

            },
             
            clear() {
                this.teachtable = {
                        lecturersId: null,
                        courseId: null,
                        semesterId: null,
                        daysId: null,
                        roomId: null,
                        // email: "",
                        // academicYear: "",
                        // startTime: "",
                        // endTime:"",
                        // annotation: "",
                }
                //this.$refs.form.reset();
            },

            /* eslint-enable no-console */
        },

        mounted() {
            this.getLecturers();
            this.getSemesters();
            this.getCourses();
            this.getRooms();
            //this.getDayss();
            console.log(this.days)
        },
        created() {
            this.getDayss();
        }
    };
</script>
