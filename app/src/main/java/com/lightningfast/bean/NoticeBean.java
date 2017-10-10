package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/18
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class NoticeBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : {"list":[{"id":3,"bind_keyword":"水电费hi","group_id":0,"source":0,"content":"阿斯顿发送到发送到","type":"1","create_time":"2017-08-18 18:39:36","status":1,"is_read":0},{"id":2,"bind_keyword":"测试","group_id":0,"source":0,"content":"系统要上线了啊","type":"1","create_time":"2017-08-16 18:19:12","status":1,"is_read":0}],"page":"1","total_count":2,"no_more":0}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"id":3,"bind_keyword":"水电费hi","group_id":0,"source":0,"content":"阿斯顿发送到发送到","type":"1","create_time":"2017-08-18 18:39:36","status":1,"is_read":0},{"id":2,"bind_keyword":"测试","group_id":0,"source":0,"content":"系统要上线了啊","type":"1","create_time":"2017-08-16 18:19:12","status":1,"is_read":0}]
         * page : 1
         * total_count : 2
         * no_more : 0
         */

        private String page;
        private int total_count;
        private int no_more;
        private List<ListBean> list;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public int getNo_more() {
            return no_more;
        }

        public void setNo_more(int no_more) {
            this.no_more = no_more;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 3
             * bind_keyword : 水电费hi
             * group_id : 0
             * source : 0
             * content : 阿斯顿发送到发送到
             * type : 1
             * create_time : 2017-08-18 18:39:36
             * status : 1
             * is_read : 0
             */

            private String id;
            private String bind_keyword;
            private String group_id;
            private String source;
            private String content;
            private String type;
            private String create_time;
            private String status;
            private int is_read;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBind_keyword() {
                return bind_keyword;
            }

            public void setBind_keyword(String bind_keyword) {
                this.bind_keyword = bind_keyword;
            }

            public String getGroup_id() {
                return group_id;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getIs_read() {
                return is_read;
            }

            public void setIs_read(int is_read) {
                this.is_read = is_read;
            }
        }
    }
}
