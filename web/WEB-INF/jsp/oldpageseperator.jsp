<c:choose>
  <c:when test="${currentPage==1}">
    Prev
  </c:when>
  <c:otherwise>
    <a href="${pageContext.request.contextPath}/page?pageNumber=${currentPage-1}">Prev</a>
  </c:otherwise>
</c:choose>
    <span>
      <c:forEach var="i" begin="1" end="${totalPages}" >
        <c:choose>
          <c:when test="${i==currentPage}">
            ${i}
          </c:when>
          <c:otherwise>
            <a href="${pageContext.request.contextPath}/page?pageNumber=${i}">
              ${i}
            </a>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </span>
<c:choose>
  <c:when test="${currentPage==totalPages}">
    Next
  </c:when>
  <c:otherwise>
    <a href="${pageContext.request.contextPath}/page?pageNumber=${currentPage+1}">Next</a>
  </c:otherwise>
</c:choose>