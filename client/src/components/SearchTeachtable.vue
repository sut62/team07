<template>
    <v-card class="mx-auto cyan lighten-4" raised>
        <v-card-title>
            ตารางสอนอาจารย์
            <v-spacer></v-spacer>
            <v-text-field
                    single-line
                    hide-details
                    v-model="search"
                    label="ค้นหาตารางสอน"
                    append-icon="mdi-account-search"
            ></v-text-field>
        </v-card-title>
        <v-data-table
                :headers="headers"
                no-results-text="ไม่พบข้อมูล"
                no-data-text="ไม่มีข้อมูลตาราง"
                :search="search"
                :items="teachTable"
        ></v-data-table>
    </v-card>
</template>

<script>
    export default {
        data: () => ({
            headers: [
                { text: 'ชื่ออาจารย์',  value: "lecturer.name"},
                { text: 'Email', value: 'email' },
                { text: 'วิชาที่สอน', value: 'course.name' },
                { text: 'ภาคการศึกษา', value: 'semester.sem' },
                { text: 'ปีการศึกษา', value: 'academicYear' },
                { text: 'วัน', value: 'days.name' },
                { text: 'ห้อง', value: 'room.name' },
                { text: 'เวลาเริ่ม', value: 'startTime' },
                { text: 'หมดเวลา', value: 'endTime' },
                { text: 'หมายเหตุ', value: 'annotation' },
            ],
            search: null,
            teachTable: []
        }),
        methods: {
            async getTeachtable() {
                await this.$http.get('teachtable').then(response => {
                    this.teachTable = response.data
                })
            }
        },
        async created() {
            await this.getTeachtable()
        },
    };
</script>