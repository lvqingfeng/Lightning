package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/8/1
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class ImageBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : {"id_card_front_img":"dev.sdkfenqi.com/uploads/20170801/c20892cb6116485f59007cc37f9436be.jpg","id_card_after_img":"dev.sdkfenqi.com/uploads/20170801/6983c1658d1960357a116ef7317f7647.jpg","person_avatar_card":"dev.sdkfenqi.com/uploads/20170801/422bf3b4bb21e43b93d6dd0173976811.jpg","bank_img":"dev.sdkfenqi.com/uploads/20170801/a3664b7ee0e0ef4661771fc19f66f1f0.jpg"}
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
         * id_card_front_img : dev.sdkfenqi.com/uploads/20170801/c20892cb6116485f59007cc37f9436be.jpg
         * id_card_after_img : dev.sdkfenqi.com/uploads/20170801/6983c1658d1960357a116ef7317f7647.jpg
         * person_avatar_card : dev.sdkfenqi.com/uploads/20170801/422bf3b4bb21e43b93d6dd0173976811.jpg
         * bank_img : dev.sdkfenqi.com/uploads/20170801/a3664b7ee0e0ef4661771fc19f66f1f0.jpg
         */

        private String id_card_front_img;
        private String id_card_after_img;
        private String person_avatar_card;
        private String bank_img;

        public String getId_card_front_img() {
            return id_card_front_img;
        }

        public void setId_card_front_img(String id_card_front_img) {
            this.id_card_front_img = id_card_front_img;
        }

        public String getId_card_after_img() {
            return id_card_after_img;
        }

        public void setId_card_after_img(String id_card_after_img) {
            this.id_card_after_img = id_card_after_img;
        }

        public String getPerson_avatar_card() {
            return person_avatar_card;
        }

        public void setPerson_avatar_card(String person_avatar_card) {
            this.person_avatar_card = person_avatar_card;
        }

        public String getBank_img() {
            return bank_img;
        }

        public void setBank_img(String bank_img) {
            this.bank_img = bank_img;
        }
    }
}
