package com.example.nice.everywhere.bean;

import java.util.List;

public class RouteDetalBean {

    /**
     * code : 0
     * desc :
     * result : {"carousel":["http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg"],"route":{"id":204,"banmiID":59,"cityID":54,"priceInCents":190,"title":"清迈美食","intro":"舌尖上的\u201c泰北玫瑰\u201d","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg","videoURL":"","sequence":-880,"description":"你好，我是Berry。目前已在清迈旅居近3年，骑着摩托车走遍了清迈的大街小巷，我爱这里的美食，也爱逛这里的小店。生活在这里，每天都在享受美好的慢时光。\n\n不知道你听没听过邓丽君的《小城故事》？歌中讲述的就是素有\u201c泰北玫瑰\u201d之称的清迈。而这座有故事的小城，由文艺与诗意组成。街头巷尾随处可见寺庙与佛堂，咖啡馆和艺术小店\u2026\u2026空气中弥漫着祥和安宁的气息。而这里不只是艺术家的乐园，更是吃货的小资天堂。在三面环山的清迈，每天都可以找一家景观餐厅坐下来，一边享美食，一边欣赏山景、河景。\n\n我这次想带你走到清迈重点游览的5大区域，吃最地道的美食，有正宗泰北菜，泰式融合料理，小巷里的趣味小吃，当然也少不了文艺范儿的咖啡馆、甜品店，还有high翻天的酒吧，还要告诉你，到底去哪里可以让你真正静心、清心；又是哪里能让你零距离接触大自然。咱们从街头吃到巷尾，从老城逛到丛林，享受文艺慢生活的同时，嘴巴可不能慢下来喔！","shareTitle":"清迈美食","shareContent":"舌尖上的\u201c泰北玫瑰\u201d","purchasedTimes":1683,"price":"1.9","isPurchased":false,"isCollected":false,"city":"泰国","shareURL":"http://banmi.com/app2017/route3.html?id=204","shareImageWechat":"http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg"},"banmi":{"id":59,"name":"Berry Pan","location":"清迈","occupation":"清迈民宿店主","introduction":"你好。我是Berry Pan。一个生于水米之乡江苏的\u201c佛系\u201d女子，生活随性，开朗，爱旅行，爱摄影，爱深潜（aow），甚至拿到了PADI的高氧证。如果哪天去了海岛做了潜水教练，大概也不奇怪。因为一次宴会后的机缘巧合结识了现在的合作伙伴，我便辞职旅居清迈近3年，曾开着摩托车走遍了清迈的大街小巷，也曾自驾3次竖穿泰国南北府，横贯泰国东西部，踏遍了泰南众多岛屿。对泰国，可以说是了如指掌。","photo4":"http://cdn.banmi.com/banmiapp/rahdna/1522380641510_6d8284ecbda2df1148f23332075b5e22.jpg","photo":"http://cdn.banmi.com/banmiapp/rahdna/1522380641510_6d8284ecbda2df1148f23332075b5e22.jpg"},"reviews":[{"reviewID":2866,"userName":"花小拉拉","userPhoto":"http://media.banmi.com/photos/1451935668554_3abef9de7a7af03abd766b0d9a01576f","content":"4月要去清迈啦，期待期待\u2026","createdAt":"2月前","images":[]},{"reviewID":2861,"userName":"多拉小米","userPhoto":"http://media.banmi.com/photos/1451919328720_bfb83097e224dec0f66b24b8b2b4c432","content":"几乎就是照着这个路线走的，体验很棒！","createdAt":"2月前","images":[]},{"reviewID":2827,"userName":"清清","userPhoto":"http://media.banmi.com/photos/1453256604738_2c8d42cc728c8aa6028c444b7959c2af","content":"清迈真的是百去不厌，攻略推荐的有几家店还挺有特色的，又有去的理由了，哈哈哈","createdAt":"2月前","images":[]}],"reviewsCount":36}
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
         * carousel : ["http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg"]
         * route : {"id":204,"banmiID":59,"cityID":54,"priceInCents":190,"title":"清迈美食","intro":"舌尖上的\u201c泰北玫瑰\u201d","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg","videoURL":"","sequence":-880,"description":"你好，我是Berry。目前已在清迈旅居近3年，骑着摩托车走遍了清迈的大街小巷，我爱这里的美食，也爱逛这里的小店。生活在这里，每天都在享受美好的慢时光。\n\n不知道你听没听过邓丽君的《小城故事》？歌中讲述的就是素有\u201c泰北玫瑰\u201d之称的清迈。而这座有故事的小城，由文艺与诗意组成。街头巷尾随处可见寺庙与佛堂，咖啡馆和艺术小店\u2026\u2026空气中弥漫着祥和安宁的气息。而这里不只是艺术家的乐园，更是吃货的小资天堂。在三面环山的清迈，每天都可以找一家景观餐厅坐下来，一边享美食，一边欣赏山景、河景。\n\n我这次想带你走到清迈重点游览的5大区域，吃最地道的美食，有正宗泰北菜，泰式融合料理，小巷里的趣味小吃，当然也少不了文艺范儿的咖啡馆、甜品店，还有high翻天的酒吧，还要告诉你，到底去哪里可以让你真正静心、清心；又是哪里能让你零距离接触大自然。咱们从街头吃到巷尾，从老城逛到丛林，享受文艺慢生活的同时，嘴巴可不能慢下来喔！","shareTitle":"清迈美食","shareContent":"舌尖上的\u201c泰北玫瑰\u201d","purchasedTimes":1683,"price":"1.9","isPurchased":false,"isCollected":false,"city":"泰国","shareURL":"http://banmi.com/app2017/route3.html?id=204","shareImageWechat":"http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg"}
         * banmi : {"id":59,"name":"Berry Pan","location":"清迈","occupation":"清迈民宿店主","introduction":"你好。我是Berry Pan。一个生于水米之乡江苏的\u201c佛系\u201d女子，生活随性，开朗，爱旅行，爱摄影，爱深潜（aow），甚至拿到了PADI的高氧证。如果哪天去了海岛做了潜水教练，大概也不奇怪。因为一次宴会后的机缘巧合结识了现在的合作伙伴，我便辞职旅居清迈近3年，曾开着摩托车走遍了清迈的大街小巷，也曾自驾3次竖穿泰国南北府，横贯泰国东西部，踏遍了泰南众多岛屿。对泰国，可以说是了如指掌。","photo4":"http://cdn.banmi.com/banmiapp/rahdna/1522380641510_6d8284ecbda2df1148f23332075b5e22.jpg","photo":"http://cdn.banmi.com/banmiapp/rahdna/1522380641510_6d8284ecbda2df1148f23332075b5e22.jpg"}
         * reviews : [{"reviewID":2866,"userName":"花小拉拉","userPhoto":"http://media.banmi.com/photos/1451935668554_3abef9de7a7af03abd766b0d9a01576f","content":"4月要去清迈啦，期待期待\u2026","createdAt":"2月前","images":[]},{"reviewID":2861,"userName":"多拉小米","userPhoto":"http://media.banmi.com/photos/1451919328720_bfb83097e224dec0f66b24b8b2b4c432","content":"几乎就是照着这个路线走的，体验很棒！","createdAt":"2月前","images":[]},{"reviewID":2827,"userName":"清清","userPhoto":"http://media.banmi.com/photos/1453256604738_2c8d42cc728c8aa6028c444b7959c2af","content":"清迈真的是百去不厌，攻略推荐的有几家店还挺有特色的，又有去的理由了，哈哈哈","createdAt":"2月前","images":[]}]
         * reviewsCount : 36
         */

        private RouteBean route;
        private BanmiBean banmi;
        private int reviewsCount;
        private List<String> carousel;
        private List<ReviewsBean> reviews;

        public RouteBean getRoute() {
            return route;
        }

        public void setRoute(RouteBean route) {
            this.route = route;
        }

        public BanmiBean getBanmi() {
            return banmi;
        }

        public void setBanmi(BanmiBean banmi) {
            this.banmi = banmi;
        }

        public int getReviewsCount() {
            return reviewsCount;
        }

        public void setReviewsCount(int reviewsCount) {
            this.reviewsCount = reviewsCount;
        }

        public List<String> getCarousel() {
            return carousel;
        }

        public void setCarousel(List<String> carousel) {
            this.carousel = carousel;
        }

        public List<ReviewsBean> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }

        public static class RouteBean {
            /**
             * id : 204
             * banmiID : 59
             * cityID : 54
             * priceInCents : 190
             * title : 清迈美食
             * intro : 舌尖上的“泰北玫瑰”
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg
             * videoURL :
             * sequence : -880
             * description : 你好，我是Berry。目前已在清迈旅居近3年，骑着摩托车走遍了清迈的大街小巷，我爱这里的美食，也爱逛这里的小店。生活在这里，每天都在享受美好的慢时光。

             不知道你听没听过邓丽君的《小城故事》？歌中讲述的就是素有“泰北玫瑰”之称的清迈。而这座有故事的小城，由文艺与诗意组成。街头巷尾随处可见寺庙与佛堂，咖啡馆和艺术小店……空气中弥漫着祥和安宁的气息。而这里不只是艺术家的乐园，更是吃货的小资天堂。在三面环山的清迈，每天都可以找一家景观餐厅坐下来，一边享美食，一边欣赏山景、河景。

             我这次想带你走到清迈重点游览的5大区域，吃最地道的美食，有正宗泰北菜，泰式融合料理，小巷里的趣味小吃，当然也少不了文艺范儿的咖啡馆、甜品店，还有high翻天的酒吧，还要告诉你，到底去哪里可以让你真正静心、清心；又是哪里能让你零距离接触大自然。咱们从街头吃到巷尾，从老城逛到丛林，享受文艺慢生活的同时，嘴巴可不能慢下来喔！
             * shareTitle : 清迈美食
             * shareContent : 舌尖上的“泰北玫瑰”
             * purchasedTimes : 1683
             * price : 1.9
             * isPurchased : false
             * isCollected : false
             * city : 泰国
             * shareURL : http://banmi.com/app2017/route3.html?id=204
             * shareImageWechat : http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg
             */

            private int id;
            private int banmiID;
            private int cityID;
            private int priceInCents;
            private String title;
            private String intro;
            private String cardURL;
            private String videoURL;
            private int sequence;
            private String description;
            private String shareTitle;
            private String shareContent;
            private int purchasedTimes;
            private String price;
            private boolean isPurchased;
            private boolean isCollected;
            private String city;
            private String shareURL;
            private String shareImageWechat;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getBanmiID() {
                return banmiID;
            }

            public void setBanmiID(int banmiID) {
                this.banmiID = banmiID;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            public int getPriceInCents() {
                return priceInCents;
            }

            public void setPriceInCents(int priceInCents) {
                this.priceInCents = priceInCents;
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

            public String getCardURL() {
                return cardURL;
            }

            public void setCardURL(String cardURL) {
                this.cardURL = cardURL;
            }

            public String getVideoURL() {
                return videoURL;
            }

            public void setVideoURL(String videoURL) {
                this.videoURL = videoURL;
            }

            public int getSequence() {
                return sequence;
            }

            public void setSequence(int sequence) {
                this.sequence = sequence;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareContent() {
                return shareContent;
            }

            public void setShareContent(String shareContent) {
                this.shareContent = shareContent;
            }

            public int getPurchasedTimes() {
                return purchasedTimes;
            }

            public void setPurchasedTimes(int purchasedTimes) {
                this.purchasedTimes = purchasedTimes;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public boolean isIsPurchased() {
                return isPurchased;
            }

            public void setIsPurchased(boolean isPurchased) {
                this.isPurchased = isPurchased;
            }

            public boolean isIsCollected() {
                return isCollected;
            }

            public void setIsCollected(boolean isCollected) {
                this.isCollected = isCollected;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }

            public String getShareImageWechat() {
                return shareImageWechat;
            }

            public void setShareImageWechat(String shareImageWechat) {
                this.shareImageWechat = shareImageWechat;
            }
        }

        public static class BanmiBean {
            /**
             * id : 59
             * name : Berry Pan
             * location : 清迈
             * occupation : 清迈民宿店主
             * introduction : 你好。我是Berry Pan。一个生于水米之乡江苏的“佛系”女子，生活随性，开朗，爱旅行，爱摄影，爱深潜（aow），甚至拿到了PADI的高氧证。如果哪天去了海岛做了潜水教练，大概也不奇怪。因为一次宴会后的机缘巧合结识了现在的合作伙伴，我便辞职旅居清迈近3年，曾开着摩托车走遍了清迈的大街小巷，也曾自驾3次竖穿泰国南北府，横贯泰国东西部，踏遍了泰南众多岛屿。对泰国，可以说是了如指掌。
             * photo4 : http://cdn.banmi.com/banmiapp/rahdna/1522380641510_6d8284ecbda2df1148f23332075b5e22.jpg
             * photo : http://cdn.banmi.com/banmiapp/rahdna/1522380641510_6d8284ecbda2df1148f23332075b5e22.jpg
             */

            private int id;
            private String name;
            private String location;
            private String occupation;
            private String introduction;
            private String photo4;
            private String photo;

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

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getPhoto4() {
                return photo4;
            }

            public void setPhoto4(String photo4) {
                this.photo4 = photo4;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }

        public static class ReviewsBean {
            /**
             * reviewID : 2866
             * userName : 花小拉拉
             * userPhoto : http://media.banmi.com/photos/1451935668554_3abef9de7a7af03abd766b0d9a01576f
             * content : 4月要去清迈啦，期待期待…
             * createdAt : 2月前
             * images : []
             */

            private int reviewID;
            private String userName;
            private String userPhoto;
            private String content;
            private String createdAt;
            private List<?> images;

            public int getReviewID() {
                return reviewID;
            }

            public void setReviewID(int reviewID) {
                this.reviewID = reviewID;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }
        }
    }
}
