<template>
    <div v-loading="loading">
        <div class="wrapper1"><div class="div1"></div></div>
        <el-table
                :data="tableData"
                border
                height="900"
                style="width: 100%"
                sortable="custom"
                @sort-change="onSortChange"
                :row-class-name="rowClassName"
                ref="table"
                @selection-change="handleSelectionChange"
        >
            <slot>
                <!-- column definitions here -->
            </slot>
        </el-table>

        <el-row v-if="!nonePagination" type="flex" justify="end" class="mg-top-20 page-mobile">
            <el-pagination
                    background
                    @current-change="renderTable"
                    :current-page.sync="tableConfig.currentPage"
                    :page-size="pageSize"
                    layout="total, prev, pager, next"
                    :total="tableConfig.totalElements"
            />
        </el-row>
    </div>
</template>

<script>
    /**
     *  Example: go page : /dataTableExample
     */
    import AlertService from "@/services/AlertService";

    export default {
        name: "DataTable",
        props: {
            logging: {
                type: Boolean,
                default: false
            } /* set true if you want DataTable log running actions. */,

            serverPagination: {
                type: Boolean,
                default: true
            }, /* weather to paging server side or on client side */

            data: Array /* set data for table when use client paging */,

            currentPage: {
                type: Number,
                default: 1
            },
            getTableDataFn: Function,
            /* get table data function, this function must return Promise that response data has two field: content, totalElements */
            pageSize: {
                type: Number,
                default: 10
            },

            sortMethod: Function
            /* set sortMethod when use client sort, accept 2 parameters: table data and sort option, must return table data after sort */,

            filterMethod: Function /* client side filter function */,

            filterRequest: Object /* when filter request change , DataTable auto call getTableDataFn to get new data */,

            rowClassName: Function,

            nonePagination:{
                type: Boolean,
                default: false
            },

            clientSort:{
                type: Boolean,
                default: false
            },

            multipleSelection: {
                type: Array,
            }
        },
        data() {
            return {
                tableData: [],
                tableConfig: {
                    totalElements: 0,
                    currentPage: this.currentPage,
                    sort: {
                        sortDirection: "DESC",
                        sortField: ""
                    }
                },
                rowSelection: [],
                loading: false
            };
        },
        mounted() {
            this.renderTable();
            let triggerScroll = null;

            this.$nextTick(() => {
                const tableEl = this.$el.querySelector('.el-table__body-wrapper');
                const element = this.$el.querySelector('.el-table__body');

                if (this.$el.querySelector('.is-scrolling-none') == null) { // tạo và gán sự kiện scroll cho thanh scroll ngang bên trên
                    let tableHeader = this.$el.querySelector('.el-table__header');
                    let table = this.$el.querySelector('.el-table');

                    const fullWidth = tableHeader.offsetWidth != 0 ? (tableHeader.offsetWidth + "px") : tableHeader.style.width;
                    const tableWidth = table.offsetWidth != 0 ? (table.offsetWidth + "px") : table.style.width;
                    this.$el.querySelector('.div1').style.width = fullWidth;
                    this.$el.querySelector('.wrapper1').style.width = tableWidth;

                    this.$el.querySelector('.wrapper1').addEventListener('scroll', (event) => {
                        // prevent infinite trigger scroll
                        if(triggerScroll != "table"){
                            triggerScroll = "top";
                            this.scrollTable(event.currentTarget.scrollLeft);
                        } else {
                            triggerScroll = null;
                        }
                    });

                    tableEl.addEventListener('scroll', (event) => {
                        // prevent infinite trigger scroll
                        if(triggerScroll != "top"){
                            triggerScroll = "table";
                            this.scrollTop(event.currentTarget.scrollLeft);
                        } else {
                            triggerScroll = null;
                        }
                    });
                }
                else if (this.$el.querySelector('.is-scrolling-none').length !== 0) { // nếu ko cần scroll ngang => ẩn scroll ngang bên trên
                    this.$el.querySelector('.div1').style.height = 0 + 'px';
                    this.$el.querySelector('.wrapper1').style.height = 0 + 'px';
                }
                // gắn sự kiện scroll ngang, dọc bằng giữ và kéo chuột
                let isDown = false;
                let startX, scrollLeft = 0, startY, scrollTop = 0;
                element.addEventListener('mousedown', (e) => {
                    isDown = true;
                    element.classList.add('active');
                    startX = e.pageX - element.offsetLeft;
                    startY = e.pageY - element.offsetTop;
                    scrollLeft = tableEl.scrollLeft;
                    scrollTop = tableEl.scrollTop;
                });
                let moveListener = (e) => {
                    if(!isDown) return;
                    e.preventDefault();
                    const x = e.pageX - element.offsetLeft;
                    const y = e.pageY - element.offsetTop;
                    const walkX = (x - startX) * 3;
                    const walkY = (y - startY) * 3;
                    if (walkX !== 0) {
                        tableEl.scrollLeft = scrollLeft - walkX;
                    }
                    if (walkY !== 0) {
                        tableEl.scrollTop = scrollTop - walkY;
                    }
                };
                element.addEventListener('mousemove', moveListener);
                let upListener = () => {
                    isDown = false;
                    element.classList.remove('active');
                };
                element.addEventListener('mouseup', upListener);
                element.addEventListener('mouseleave', upListener);
            })
        },
        watch: {
            /* listen pageSize property is changed then re-render table with new pageSize  */
            pageSize: function () {
                this.log("Chang pageSize : ", this.pageSize);
                this.renderTable();
            },
            /* listen filterRequest property is changed then re-render table with new filterRequest */
            filterRequest: {
                deep: true,
                handler() {
                    this.log("change filterRequest: ", this.filterRequest);
                    this.renderTable();
                }
            },

            data: {
                handler(){
                    this.renderTable();
                },
                deep: true
            }
        },
        methods: {
            scrollTop(val){
                this.$el.querySelector('.wrapper1').scrollLeft = val;
            },
            scrollTable(val){
                this.$el.querySelector('.el-table__body-wrapper').scrollLeft = val;
            },
            log() {
                if (this.logging) {
                    if (arguments.length == 1) {
                        console.log(arguments[0]);
                    } else {
                        console.log(...arguments);
                    }
                }
            },
            /* render data table */
            renderTable() {
                if (this.serverPagination) {
                    this.getData();
                } else {
                    if(!this.nonePagination){
                        const startIndex = (this.tableConfig.currentPage - 1) * this.pageSize;
                        const endIndex = this.tableConfig.currentPage * this.pageSize;
                            if (this.data.length - 1 >= startIndex) {
                                let temp = this.data;
                            if (this.filterMethod) {
                                temp = this.filterMethod(temp, this.filterRequest);
                            }
                            if (this.sortMethod) {
                                temp = this.sortMethod(temp, this.tableConfig.sort);
                            }
                            this.tableData = temp.slice(startIndex, endIndex);
                        }
                    }else{
                        this.tableData = this.data;
                    }
                }
            },
            /* get data using getDataTableFn */
            async getData() {
                let pageRequest = {};
                pageRequest.page = this.tableConfig.currentPage;
                pageRequest.pageSize = this.pageSize;
                this.loading = true;
                try {
                    const response = await this.getTableDataFn(
                        pageRequest,
                        this.tableConfig.sort,
                        this.filterRequest
                    );
                    this.tableData = response.content;
                    this.tableConfig.totalElements = response.totalElements;
                } catch (e) {
                    console.error(e);
                    AlertService.error(e);
                } finally {
                    this.loading = false;
                }
            },
            /* handler for event change sort option of element ui table. */
            onSortChange(sort) {
                this.log("DataTable change sort option ", sort);
                if (sort.order) {
                    this.tableConfig.sort.sortField = sort.prop;
                    this.tableConfig.sort.sortDirection =
                        sort.order == "ascending" ? "ASC" : "DESC";
                } else {
                    this.tableConfig.sort.sortField = null;
                }
                // get table data with new sort change.
                if(!this.clientSort){
                    this.getData();
                }
            },
            /* move to specific page */
            toPage(page) {
                this.log("Move to page ", page);
                this.tableConfig.currentPage = page;
                // get table data with new currentPage
                this.getData();
            },
            /* reload data of current page */
            reload() {
                this.log("Reload DataTable");
                this.getData();
            },
            handleSelectionChange(val) {
                this.rowSelection = val;
                this.$emit("update:multipleSelection",val);
            }
        }
    };
</script>

<style scoped>

</style>
