<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">编辑</div>
                <span></span>
                <a @click="close" class="window-close">
                    <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                </a>
            </div>
        </div>
        <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
            <FormItem label="航班号" prop="title">
                <Input v-model="form.title" clearable maxlength="240" show-word-limit placeholder="请输入航班号..." style="width:570px" />
            </FormItem>
            <FormItem label="航空公司" prop="company">
                <Select v-model="form.company" clearable placeholder="请选择航空公司..." style="width:570px">
                    <Option value="中国国航">中国国航</Option>
                    <Option value="南方航空">南方航空</Option>
                    <Option value="东方航空">东方航空</Option>
                    <Option value="海南航空">海南航空</Option>
                    <Option value="国泰航空">国泰航空</Option>
                    <Option value="深圳航空">深圳航空</Option>
                    <Option value="厦门航空">厦门航空</Option>
                </Select>
            </FormItem>
            <FormItem label="起飞机场" prop="ji1">
                <Select v-model="form.ji1" clearable placeholder="请选择起飞机场..." style="width:570px">
                    <Option value="北京首都国际机场">北京首都国际机场</Option>
                    <Option value="上海浦东国际机场">上海浦东国际机场</Option>
                    <Option value="广州白云国际机场">广州白云国际机场</Option>
                    <Option value="成都双流国际机场">成都双流国际机场</Option>
                    <Option value="西安咸阳国际机场">西安咸阳国际机场</Option>
                    <Option value="重庆江北国际机场">重庆江北国际机场</Option>
                    <Option value="上海虹桥国际机场">上海虹桥国际机场</Option>
                    <Option value="深圳宝安国际机场">深圳宝安国际机场</Option>
                    <Option value="昆明长水国际机场">昆明长水国际机场</Option>
                </Select>
            </FormItem>
            <FormItem label="到达机场" prop="ji2">
                <Select v-model="form.ji2" clearable placeholder="请选择到达机场..." style="width:570px">
                    <Option value="北京首都国际机场">北京首都国际机场</Option>
                    <Option value="上海浦东国际机场">上海浦东国际机场</Option>
                    <Option value="广州白云国际机场">广州白云国际机场</Option>
                    <Option value="成都双流国际机场">成都双流国际机场</Option>
                    <Option value="西安咸阳国际机场">西安咸阳国际机场</Option>
                    <Option value="重庆江北国际机场">重庆江北国际机场</Option>
                    <Option value="上海虹桥国际机场">上海虹桥国际机场</Option>
                    <Option value="深圳宝安国际机场">深圳宝安国际机场</Option>
                    <Option value="昆明长水国际机场">昆明长水国际机场</Option>
                </Select>
            </FormItem>
            <FormItem label="机票价格" prop="price">
                <Input v-model="form.price" clearable maxlength="240" show-word-limit placeholder="请输入机票价格..." style="width:570px" />
            </FormItem>
            <FormItem label="起飞时间" prop="time1">
                <DatePicker type="datetime" format="yyyy-MM-dd HH:mm" @on-change="changeTime1" :placeholder="form.time1" clearable style="width:570px"></DatePicker>
            </FormItem>
            <FormItem label="到达时间" prop="time2">
                <DatePicker type="datetime" format="yyyy-MM-dd HH:mm" @on-change="changeTime2" :placeholder="form.time2" clearable style="width:570px"></DatePicker>
            </FormItem>
            <FormItem label="登机口" prop="inKou">
                <Select v-model="form.inKou" clearable placeholder="请选择登机口..." style="width:570px">
                    <Option value="登机口1">登机口1</Option>
                    <Option value="登机口2">登机口2</Option>
                    <Option value="登机口3">登机口3</Option>
                    <Option value="登机口4">登机口4</Option>
                    <Option value="登机口5">登机口5</Option>
                    <Option value="登机口6">登机口6</Option>
                    <Option value="登机口7">登机口7</Option>
                    <Option value="登机口8">登机口8</Option>
                    <Option value="登机口9">登机口9</Option>
                </Select>
            </FormItem>
            <Form-item class="br">
                <Button @click="handleSubmit" :loading="submitLoading" type="primary">提交并保存</Button>
                <Button @click="handleReset">重置</Button>
                <Button type="dashed" @click="close">关闭</Button>
            </Form-item>
        </Form>
    </Card>
</div>
</template>

<script>
import {
    editFlight
} from "./api.js";
export default {
    name: "edit",
    components: {},
    props: {
        data: Object
    },
    data() {
        return {
            submitLoading: false,
            form: {
                title: "",
                company: "",
                ji1: "",
                ji2: "",
                price: "",
                time1: "",
                time2: "",
            },
            formValidate: {}
        };
    },
    methods: {
        init() {
            this.handleReset();
            this.form = this.data;
        },
        changeTime1(e) {
            this.form.time1 = e;
        },
        changeTime2(e) {
            this.form.time2 = e;
        },
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    editFlight(this.form).then(res => {
                        this.submitLoading = false;
                        if (res.success) {
                            this.$Message.success("操作成功");
                            this.submited();
                        }
                    });
                }
            });
        },
        close() {
            this.$emit("close", true);
        },
        submited() {
            this.$emit("submited", true);
        }
    },
    mounted() {
        this.init();
    }
};
</script>

<style lang="less">
.edit-head {
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;

    .back-title {
        color: #515a6e;
        display: flex;
        align-items: center;
    }

    .head-name {
        display: inline-block;
        height: 20px;
        line-height: 20px;
        font-size: 16px;
        color: #17233d;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .window-close {
        z-index: 1;
        font-size: 12px;
        position: absolute;
        right: 0px;
        top: -5px;
        overflow: hidden;
        cursor: pointer;

        .ivu-icon-ios-close {
            color: #999;
            transition: color .2s ease;
        }
    }

    .window-close .ivu-icon-ios-close:hover {
        color: #444;
    }
}
</style>
