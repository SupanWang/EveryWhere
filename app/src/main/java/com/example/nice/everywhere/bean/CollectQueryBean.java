package com.example.nice.everywhere.bean;

import java.util.List;

public class CollectQueryBean {

    /**
     * code : 0
     * desc : 处理成功
     * result : {"page":1,"limit":20,"count":5,"collectedRoutes":[{"id":204,"cityID":54,"title":"清迈美食","intro":"舌尖上的\u201c泰北玫瑰\u201d","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"},{"id":201,"cityID":50,"title":"电影中的台湾","intro":"跟着镜头寻找宝岛小确幸","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521770663571_a5bf05381132e3ffe8add4821f1cba07.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"},{"id":202,"cityID":37,"title":"雍和宫〜北新桥","intro":"爱上历史深处的文艺小巷","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1522286621202_7b91111fb2dffe11b646759be235f7e7.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"},{"id":199,"cityID":53,"title":"厦门·鼓浪屿","intro":"文艺情怀厦之味","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521714816208_c314aeb52357c354eb51ad82e129864c.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"},{"id":208,"cityID":54,"title":"普吉岛","intro":"聆听大海的心跳","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1523445048228_79dfbc52b60dc9bce12b43ac193c233e.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"}]}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * page : 1
         * limit : 20
         * count : 5
         * collectedRoutes : [{"id":204,"cityID":54,"title":"清迈美食","intro":"舌尖上的\u201c泰北玫瑰\u201d","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"},{"id":201,"cityID":50,"title":"电影中的台湾","intro":"跟着镜头寻找宝岛小确幸","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521770663571_a5bf05381132e3ffe8add4821f1cba07.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"},{"id":202,"cityID":37,"title":"雍和宫〜北新桥","intro":"爱上历史深处的文艺小巷","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1522286621202_7b91111fb2dffe11b646759be235f7e7.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"},{"id":199,"cityID":53,"title":"厦门·鼓浪屿","intro":"文艺情怀厦之味","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521714816208_c314aeb52357c354eb51ad82e129864c.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"},{"id":208,"cityID":54,"title":"普吉岛","intro":"聆听大海的心跳","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1523445048228_79dfbc52b60dc9bce12b43ac193c233e.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"}]
         */

        private int page;
        private int limit;
        private int count;
        private List<CollectedRoutesBean> collectedRoutes;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<CollectedRoutesBean> getCollectedRoutes() {
            return collectedRoutes;
        }

        public void setCollectedRoutes(List<CollectedRoutesBean> collectedRoutes) {
            this.collectedRoutes = collectedRoutes;
        }

        public static class CollectedRoutesBean {
            /**
             * id : 204
             * cityID : 54
             * title : 清迈美食
             * intro : 舌尖上的“泰北玫瑰”
             * priceInCents : 190
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg
             * isPurchased : false
             * createdAt : 2019-05-12
             * price : 1.90
             */

            private int id;
            private int cityID;
            private String title;
            private String intro;
            private int priceInCents;
            private String cardURL;
            private boolean isPurchased;
            private String createdAt;
            private String price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public int getPriceInCents() {
                return priceInCents;
            }

            public void setPriceInCents(int priceInCents) {
                this.priceInCents = priceInCents;
            }

            public String getCardURL() {
                return cardURL;
            }

            public void setCardURL(String cardURL) {
                this.cardURL = cardURL;
            }

            public boolean isIsPurchased() {
                return isPurchased;
            }

            public void setIsPurchased(boolean isPurchased) {
                this.isPurchased = isPurchased;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
