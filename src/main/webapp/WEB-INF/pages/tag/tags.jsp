<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container list">
    <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-center list__title">Danh sách nhãn sản phẩm</h1>
        <a href="<c:url value="/admin/tags/add"/>" class="list__icon-add">
            <i class='bx bxs-plus-circle'></i>
        </a>
    </div>
</div>

<div class="container mt-4">
    <table id="categoryTable" class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Mô tả</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhập</th>
            <th>Active</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tag" items="${tags}">
            <tr id="item${tag.id}">
                <td>${tag.id}</td>
                <td>${tag.name}</td>
                <td>${tag.description}</td>
                <td>
                    <fmt:parseDate value="${ tag.createdAt }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="dd.MM.yyyy" value="${ parsedDateTime }"/>
                </td>
                <td>
                    <c:if test="${ tag.updatedAt != null }">
                        <fmt:parseDate value="${ tag.updatedAt }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedUpdatedDateTime" type="both"/>
                        <fmt:formatDate pattern="dd.MM.yyyy" value="${ parsedUpdatedDateTime }"/>
                    </c:if>
                    <c:if test="${ tag.updatedAt == null }">
                        Chưa cập nhập
                    </c:if>
                </td>
                <td>${tag.active}</td>
                <td>
                    <a class="btn btn-primary btn-sm" href="<c:url value="/admin/tags/edit/${tag.id}"/>">
                        <i class='bx bxs-edit'></i>
                    </a>

                    <c:url value="/admin/tags/delete/${tag.id}" var="deleteTag"/>
                    <button class="btn btn-danger btn-sm" href="#" onclick="deleteItem('${deleteTag}', ${tag.id})">
                        <i class='bx bx-x'></i>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>