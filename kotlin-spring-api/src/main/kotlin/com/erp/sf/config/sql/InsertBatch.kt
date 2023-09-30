
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.core.enums.SqlMethod
import com.baomidou.mybatisplus.core.injector.AbstractMethod
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo
import com.baomidou.mybatisplus.core.metadata.TableInfo
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper
import com.baomidou.mybatisplus.core.toolkit.sql.SqlInjectionUtils
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils
import lombok.Setter
import lombok.experimental.Accessors
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator
import org.apache.ibatis.executor.keygen.KeyGenerator
import org.apache.ibatis.executor.keygen.NoKeyGenerator
import org.apache.ibatis.mapping.MappedStatement
import java.util.function.Predicate

class InsertBatch : AbstractMethod {
    @Setter
    @Accessors(chain = true)
    private var predicate: Predicate<TableFieldInfo>? = null


    /**
     * 默认方法名
     *
     * @param predicate 字段筛选条件
     */
    constructor(predicate: Predicate<TableFieldInfo>?) : super("insertBatch") {
        this.predicate = predicate
    }

    /**
     * @param name      方法名
     * @param predicate 字段筛选条件
     * @since 3.5.0
     */

    override fun injectMappedStatement(
        mapperClass: Class<*>?,
        modelClass: Class<*>?,
        tableInfo: TableInfo
    ): MappedStatement {
        var keyGenerator: KeyGenerator? = NoKeyGenerator.INSTANCE
        val sqlMethod = SqlMethod.INSERT_ONE
        val fieldList = tableInfo.fieldList
        val insertSqlColumn = tableInfo.getKeyInsertSqlColumn(true, false) +
                filterTableFieldInfo(
                    fieldList, predicate,
                    { obj: TableFieldInfo -> obj.insertSqlColumn }, EMPTY
                )
        val columnScript = LEFT_BRACKET + insertSqlColumn.substring(0, insertSqlColumn.length - 1) + RIGHT_BRACKET
        var insertSqlProperty = tableInfo.getKeyInsertSqlProperty(true, ENTITY_DOT, false) +
                filterTableFieldInfo(
                    fieldList, predicate,
                    { i: TableFieldInfo -> i.getInsertSqlProperty(ENTITY_DOT) }, EMPTY
                )
        insertSqlProperty = LEFT_BRACKET + insertSqlProperty.substring(0, insertSqlProperty.length - 1) + RIGHT_BRACKET
        val valuesScript = SqlScriptUtils.convertForeach(insertSqlProperty, "list", null, ENTITY, COMMA)
        var keyProperty: String? = null
        var keyColumn: String? = null
        // 表包含主键处理逻辑,如果不包含主键当普通字段处理
        if (tableInfo.havePK()) {
            if (tableInfo.idType == IdType.AUTO) {
                /* 自增主键 */
                keyGenerator = Jdbc3KeyGenerator.INSTANCE
                keyProperty = tableInfo.keyProperty
                // 去除转义符
                keyColumn = SqlInjectionUtils.removeEscapeCharacter(tableInfo.keyColumn)
            } else {
                if (null != tableInfo.keySequence) {
                    keyGenerator = TableInfoHelper.genKeyGenerator(methodName, tableInfo, builderAssistant)
                    keyProperty = tableInfo.keyProperty
                    keyColumn = tableInfo.keyColumn
                }
            }
        }
        val sql = String.format(sqlMethod.sql, tableInfo.tableName, columnScript, valuesScript)
        val sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass)
        return this.addInsertMappedStatement(
            mapperClass,
            modelClass,
            methodName,
            sqlSource,
            keyGenerator,
            keyProperty,
            keyColumn
        )
    }
}
