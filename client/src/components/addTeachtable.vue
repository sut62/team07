<template>
    <v-container>
        <v-form ref="form" v-model="valid" persistent max-width="500px">
            <v-layout text-center wrap>
                <v-container>
                    <div>
                        <p class="font-weight-black display-1">Add Teach Table</p>
                    </div>
                    <v-row justify="center">
                        <v-col class="d-flex" cols="12" sm="5">
                            <v-select
                                    label="ชื่ออาจารย์"
                                    outlined
                                    v-model="teachtable.nameteacherId"
                                    :items="nameteacher"
                                    item-text="sem"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'กรุณาเลือกชื่ออาจารย์']"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col class="d-flex" cols="12" sm="4">
                            <v-select
                                    label="วิชา"
                                    outlined
                                    v-model="teachtable.courseId"
                                    :items="course"
                                    item-text="name"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'กรุณาใส่วิชา']"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col class="d-flex" cols="12" sm="2">
                            <v-select
                                    label="ภาคการศึกษา"
                                    outlined
                                    v-model="teachtable.semesterId"
                                    :items="semester"
                                    item-text="sem"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'กรุณาเลือกภาคการศึกษา']"
                                    required
                            ></v-select>
                        </v-col>
                        <v-col cols="12" sm="3">
                            <v-text-field
                                    label="ปีการศึกษา"
                                    outlined
                                    v-model="teachtable.academicYear"
                                    clearable
                                    :rules="[(v) => !!v || 'กรุณาใส่ปีการศึกษา']"
                                    required
                            ></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col class="d-flex" cols="12" sm="3">
                            <v-select
                                    label="วัน"
                                    outlined
                                    v-model="teachtable.daysId"
                                    :items="days"
                                    item-text="sem"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'กรุณาเลือกวัน']"
                                    required
                            ></v-select>
                        </v-col>
                        <v-col class="d-flex" cols="12" sm="2">
                            <v-select
                                    label="ห้องเรียน"
                                    outlined
                                    v-model="teachtable.roomId"
                                    :items="room"
                                    item-text="name"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'กรุณาเลือกห้องเรียน']"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>

                    <v-row justify="center">
                        <v-col cols="12" sm="2">
                            <v-menu
                                    ref="menu"
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
                                            v-model="teachtable.startTime"
                                            label="เวลาเริ่ม"
                                            readonly
                                            v-on="on"
                                    ></v-text-field>
                                </template>
                                <v-time-picker
                                        v-if="menu3"
                                        v-model="teachtable.startTime"
                                        full-width
                                        @click:minute="$refs.menu.save(teachtable.startTime)"
                                ></v-time-picker>
                            </v-menu>
                        </v-col>
                        <v-col cols="12" sm="2">
                            <v-menu
                                    ref="menu"
                                    v-model="menu4"
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
                                        v-if="menu4"
                                        v-model="teachtable.endTime"
                                        full-width
                                        @click:minute="$refs.menu.save(teachtable.endTime)"
                                ></v-time-picker>
                            </v-menu>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="12">
                            <v-btn :disabled="!valid" color="success" @click="saveTeachtable">Save</v-btn>
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
        name: "teachtable",
        data() {
            return {
                teachtable: {
                    nameteacherId: [],
                    courseId: [],
                    semesterId: [],
                    daysId: [],
                    roomId: []
                },
                valid: false,
                menu1: false,
                menu2: false,
                menu3: false,
                menu4: false,
                nameteacher: null,
                course: null,
                semester: null,
                academicYear: null,
                days: null,
                room: null,
                startTime: null,
                endTime: null
            };
        },

        methods: {
            /* eslint-disable no-console */

            getNameteachers() {
                this.$http
                    .get("/nameteacher")
                    .then(response => {
                        this.nameteacher = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            getCourses() {
                this.$http
                    .get("/course")
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
                        this.teachtable.nameteacherId +
                        "/" +
                        this.teachtable.courseId +
                        "/" +
                        this.teachtable.semesterId +
                        "/" +
                        this.teachtable.academicYear +
                        "/" +
                        this.teachtable.days +
                        "/" +
                        this.teachtable.roomId +
                        "/" +
                        this.teachtable.startTime +
                        "/" +
                        this.teachtable.endTime ,
                        this.teachtable
                    )
                    .then(response => {
                        console.log(response);
                        alert('บันทึกข้อมูลสำเร็จ');
                        this.$refs.form.reset();
                    })
                    .catch(e => {
                        console.log(e);
                        alert('บันทึกข้อมูลไม่สำเร็จ');
                    });
            },

            clear() {
                this.$refs.form.reset();
            },

            /* eslint-enable no-console */
        },

        mounted() {
            this.getNameteachers();
            this.getSemesters();
            this.getCourses();
            this.getDayss();
            this.getRooms();
        }
    };
</script>
