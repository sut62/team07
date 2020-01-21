<template>
    <v-card class="mx-auto cyan lighten-4" raised width="50%">
        <v-card-title>ข้อมูลส่วนตัวอาจารย์</v-card-title>
        <v-card-text>
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>รหัสอาจารย์: </v-list-item-title>
                    <v-list-item-subtitle>{{ profile.lecturerCode }}</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>ชื่อ - สกุล: </v-list-item-title>
                    <v-list-item-subtitle>{{ profile.prefix.name }} {{ profile.name }}</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>รหัสผ่าน <v-btn icon @click="show = !show"><v-icon>{{ show ? 'mdi-eye-outline': 'mdi-eye-off-outline'}}</v-icon></v-btn></v-list-item-title>
                    <v-list-item-subtitle v-show="show">{{ profile.password }}</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>สังกัด</v-list-item-title>
                    <v-list-item-subtitle>{{ profile.major.institute.name }}</v-list-item-subtitle>
                    <v-list-item-subtitle>{{ profile.major.name }}</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>เพศ</v-list-item-title>
                    <v-list-item-subtitle>{{ profile.gender.name }}</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>รหัสบัตรประชาชน</v-list-item-title>
                    <v-list-item-subtitle>{{ profile.personalId }}</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>Email</v-list-item-title>
                    <v-list-item-subtitle>{{ profile.email}}</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>เบอร์โทรศัพท์</v-list-item-title>
                    <v-list-item-subtitle>{{ profile.tel }}</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
        </v-card-text>
    </v-card>
</template>

<script>
import { mapState } from 'vuex'
export default {
    data: () => ({
        show: false
    }),
    created() {
        this.$store.commit('setProfile', this.$route.params.lecturerCode)
    },
    computed: {
        ...mapState({
            profile: state => state.profile
        })
    }
}
</script>