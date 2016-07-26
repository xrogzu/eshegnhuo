package com.example.eshenghuo.bean;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/6/25.
 */
public class TestBean extends BaseBean {

    /**
     * result : 1
     * data : {"id":113,"name":"华夏银行vip免费专享3小时保洁服务！","type":"6","create_time":1463449157058,"end_time":1463536660671,"creater_id":"admin","flag":"1","content":"<p><span style=\"line-height:1.75em\"><span style=\"font-size:24px\"><span style=\"color:rgb(255, 0, 0)\"><strong>华夏银行vip&ldquo;免费&rdquo;专享3小时家庭保洁服务！<\/strong><\/span><\/span><\/span><\/p>\n\n<p><span style=\"line-height:1.75em\"><span style=\"font-size:18px\"><span style=\"color:rgb(255, 140, 0)\"><strong>华夏e生活与师傅优家团队合作，免费为华夏vip用户提供3小时家庭保洁服务！华夏银行用心为您服务，再次感谢您对华夏银行的支持！<\/strong><\/span><\/span><\/span><\/p>\n\n<p><span style=\"line-height:1.75em\"><span style=\"font-size:18px\"><span style=\"color:rgb(255, 140, 0)\"><strong>具体活动内容请查看下方活动说明：<\/strong><\/span><\/span><\/span><\/p>\n\n<p><span style=\"line-height:1.75em\"><img alt=\"\" src=\"http://www.shequchina.cn:80/hxb/uploadImg/b0b467b5-1560-4870-ae03-e812f2abb8ff.jpg\" style=\"width:100%\" /><\/span><\/p>\n\n<p><span style=\"line-height:1.75em\"><img alt=\"\" src=\"http://www.shequchina.cn:80/hxb/uploadImg/fb134ce3-f675-4d76-ac7f-4f2bbd54a273.jpg\" style=\"width:100%\" /><\/span><\/p>\n\n<p><span style=\"line-height:1.75em\"><img alt=\"\" src=\"http://www.shequchina.cn:80/hxb/uploadImg/c1958eb3-b744-4c4e-8cda-4875b7fa4273.jpg\" style=\"width:100%\" /><\/span><\/p>\n\n<p><span style=\"line-height:1.75em\"><img alt=\"\" src=\"http://www.shequchina.cn:80/hxb/uploadImg/c979b104-c411-4096-b3ab-9d916c15787d.jpg\" style=\"width:100%\" /><\/span><\/p>\n\n<p><span style=\"line-height:1.75em\"><strong><span style=\"color:rgb(255, 0, 0)\"><span style=\"font-size:20px\">华夏e生活咨询热线：0532-66888577<\/span><\/span><\/strong><\/span><\/p>\n","imageurl":"http://www.shequchina.cn:80/hxb/uploadImg/8cd719d1-ddb8-42ee-afa5-7fd3870ed5b8.jpg","readedquantity":0,"is_push":"0"}
     */

    private int result;
    /**
     * id : 113
     * name : 华夏银行vip免费专享3小时保洁服务！
     * type : 6
     * create_time : 1463449157058
     * end_time : 1463536660671
     * creater_id : admin
     * flag : 1
     * content : <p><span style="line-height:1.75em"><span style="font-size:24px"><span style="color:rgb(255, 0, 0)"><strong>华夏银行vip&ldquo;免费&rdquo;专享3小时家庭保洁服务！</strong></span></span></span></p>

     <p><span style="line-height:1.75em"><span style="font-size:18px"><span style="color:rgb(255, 140, 0)"><strong>华夏e生活与师傅优家团队合作，免费为华夏vip用户提供3小时家庭保洁服务！华夏银行用心为您服务，再次感谢您对华夏银行的支持！</strong></span></span></span></p>

     <p><span style="line-height:1.75em"><span style="font-size:18px"><span style="color:rgb(255, 140, 0)"><strong>具体活动内容请查看下方活动说明：</strong></span></span></span></p>

     <p><span style="line-height:1.75em"><img alt="" src="http://www.shequchina.cn:80/hxb/uploadImg/b0b467b5-1560-4870-ae03-e812f2abb8ff.jpg" style="width:100%" /></span></p>

     <p><span style="line-height:1.75em"><img alt="" src="http://www.shequchina.cn:80/hxb/uploadImg/fb134ce3-f675-4d76-ac7f-4f2bbd54a273.jpg" style="width:100%" /></span></p>

     <p><span style="line-height:1.75em"><img alt="" src="http://www.shequchina.cn:80/hxb/uploadImg/c1958eb3-b744-4c4e-8cda-4875b7fa4273.jpg" style="width:100%" /></span></p>

     <p><span style="line-height:1.75em"><img alt="" src="http://www.shequchina.cn:80/hxb/uploadImg/c979b104-c411-4096-b3ab-9d916c15787d.jpg" style="width:100%" /></span></p>

     <p><span style="line-height:1.75em"><strong><span style="color:rgb(255, 0, 0)"><span style="font-size:20px">华夏e生活咨询热线：0532-66888577</span></span></strong></span></p>

     * imageurl : http://www.shequchina.cn:80/hxb/uploadImg/8cd719d1-ddb8-42ee-afa5-7fd3870ed5b8.jpg
     * readedquantity : 0
     * is_push : 0
     */

    private DataEntity data;

    public static TestBean objectFromData(String str) {

        return new Gson().fromJson(str, TestBean.class);
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        private int id;
        private String name;
        private String type;
        private long create_time;
        private long end_time;
        private String creater_id;
        private String flag;
        private String content;
        private String imageurl;
        private int readedquantity;
        private String is_push;

        public static DataEntity objectFromData(String str) {

            return new Gson().fromJson(str, DataEntity.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(long end_time) {
            this.end_time = end_time;
        }

        public String getCreater_id() {
            return creater_id;
        }

        public void setCreater_id(String creater_id) {
            this.creater_id = creater_id;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public int getReadedquantity() {
            return readedquantity;
        }

        public void setReadedquantity(int readedquantity) {
            this.readedquantity = readedquantity;
        }

        public String getIs_push() {
            return is_push;
        }

        public void setIs_push(String is_push) {
            this.is_push = is_push;
        }
    }
}
