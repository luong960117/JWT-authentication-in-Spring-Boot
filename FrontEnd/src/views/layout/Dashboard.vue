<template>
    <div class="layout">
        <!-- 左侧导航 -->
        <div style="text-align: center; margin: 50px;"><h1>This is Workspace</h1></div>
        <div style="text-align: center"><h1>I am working here now</h1></div>
    </div>
</template>

<script>
    export default {
        components:{
        },
        data(){
            return{
                isCollapse:false
            }
        },
        methods:{
            changeShow(){
                this.isCollapse=!this.isCollapse;
                //同步仓库---
                this.$store.commit('changeIsCollapse',this.isCollapse)

            }
        }
    }
</script>

<style lang="less" scoped>
    .layout{
        // display: flex;
        .menu{
            // width: 200px;
            background: #112f50;
            position: fixed;
            left:0;
            top:0;
            bottom:0;
        }
        .content{
            padding-left:200px;
        }
        .small{
            padding-left: 64px;
        }
    }
</style>