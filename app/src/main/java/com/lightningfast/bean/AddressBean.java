package com.lightningfast.bean;

import java.util.List;

/**
 * 作者：lv
 * 创建时间：10月29日
 * 时间：17:22
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */

public class AddressBean {

    /**
     * id : 2
     * name : 北京
     * sub : [{"id":52,"name":"北京","pro_id":2,"sub2":[{"id":500,"name":"东城区","city_id":52},{"id":501,"name":"西城区","city_id":52},{"id":502,"name":"海淀区","city_id":52},{"id":503,"name":"朝阳区","city_id":52},{"id":504,"name":"崇文区","city_id":52},{"id":505,"name":"宣武区","city_id":52},{"id":506,"name":"丰台区","city_id":52},{"id":507,"name":"石景山区","city_id":52},{"id":508,"name":"房山区","city_id":52},{"id":509,"name":"门头沟区","city_id":52},{"id":510,"name":"通州区","city_id":52},{"id":511,"name":"顺义区","city_id":52},{"id":512,"name":"昌平区","city_id":52},{"id":513,"name":"怀柔区","city_id":52},{"id":514,"name":"平谷区","city_id":52},{"id":515,"name":"大兴区","city_id":52},{"id":516,"name":"密云县","city_id":52},{"id":517,"name":"延庆县","city_id":52}]}]
     */

    private String id;
    private String name;
    private List<SubBean> sub;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubBean> getSub() {
        return sub;
    }

    public void setSub(List<SubBean> sub) {
        this.sub = sub;
    }

    public static class SubBean {
        /**
         * id : 52
         * name : 北京
         * pro_id : 2
         * sub2 : [{"id":500,"name":"东城区","city_id":52},{"id":501,"name":"西城区","city_id":52},{"id":502,"name":"海淀区","city_id":52},{"id":503,"name":"朝阳区","city_id":52},{"id":504,"name":"崇文区","city_id":52},{"id":505,"name":"宣武区","city_id":52},{"id":506,"name":"丰台区","city_id":52},{"id":507,"name":"石景山区","city_id":52},{"id":508,"name":"房山区","city_id":52},{"id":509,"name":"门头沟区","city_id":52},{"id":510,"name":"通州区","city_id":52},{"id":511,"name":"顺义区","city_id":52},{"id":512,"name":"昌平区","city_id":52},{"id":513,"name":"怀柔区","city_id":52},{"id":514,"name":"平谷区","city_id":52},{"id":515,"name":"大兴区","city_id":52},{"id":516,"name":"密云县","city_id":52},{"id":517,"name":"延庆县","city_id":52}]
         */

        private String id;
        private String name;
        private int pro_id;
        private List<Sub2Bean> sub2;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPro_id() {
            return pro_id;
        }

        public void setPro_id(int pro_id) {
            this.pro_id = pro_id;
        }

        public List<Sub2Bean> getSub2() {
            return sub2;
        }

        public void setSub2(List<Sub2Bean> sub2) {
            this.sub2 = sub2;
        }

        public static class Sub2Bean {
            /**
             * id : 500
             * name : 东城区
             * city_id : 52
             */

            private String id;
            private String name;
            private String city_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }
        }
    }
}
