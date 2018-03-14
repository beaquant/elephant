package com.djt.cbs.entity;

public class PagerInfo extends com.djt.common.PagerInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int               allpage;

    private PageNumberNav     nav;

    public PagerInfo(Integer pageSize, Integer pageIndex) {
        super(pageSize == null ? 20 : pageSize, pageIndex == null ? 1 : pageIndex);
    }

    public int getAllpage() {
        if (allpage <= 0) {
            return super.getRowsCount();
        }
        return allpage;
    }

    public void setAllpage(int allpage) {
        this.allpage = allpage;
    }

    public PageNumberNav getNav() {
        return nav;
    }

    public void setNav() {
        this.nav = new PageNumberNav();
    }

    public void setRowsCount(int rowsCount) {
        super.setRowsCount(rowsCount);
        setNav();
    }

    public class PageNumberNav {

        private int startIndex;

        private int endIndex;

        public PageNumberNav() {

            calPageRange();
        }

        private void calPageRange() {
            int startIndex = 0;
            int endIndex = 0;
            int sumIndex = 10;
            int countpage = (getRowsCount() % getPageSize() == 0) ? (getRowsCount() / getPageSize())
                : (getRowsCount() / getPageSize() + 1);
            setAllpage(countpage);
            if (getPageIndex() <= sumIndex / 2 + 1) {
                startIndex = 1;
                endIndex = sumIndex;//pager.getPageIndex() + sumIndex/2-1;  

                //当结束的索引值>总页数  startIndex
                if (endIndex > countpage) {
                    endIndex = countpage;
                }
            } else if (getPageIndex() > sumIndex / 2 + 1) {
                startIndex = getPageIndex() - sumIndex / 2;
                endIndex = getPageIndex() + sumIndex / 2 - 1;
                //当结束的索引值>总页数  
                //当结束的索引值>索引的sum值 代表的是 页面中最大显示页数  
                if (endIndex > countpage && endIndex > sumIndex) {
                    endIndex = countpage;
                    startIndex = countpage - sumIndex - 1;
                }
                //当结束的索引值<索引的sum值 代表的是 页面中最大显示页数  
                if (endIndex < sumIndex) {
                    startIndex = 1;
                    endIndex = countpage;
                }
            }
            setStartIndex(startIndex);
            setEndIndex(endIndex);
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }
    }

}
