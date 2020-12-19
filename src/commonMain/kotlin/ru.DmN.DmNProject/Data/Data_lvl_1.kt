package ru.DmN.DmNProject.Data

import kotlinx.serialization.*
import kotlinx.serialization.builtins.PairSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import ru.DmN.DmNProject.VM.throwCast

/**
 * @author  DomamaN202
 */
@Serializable
open class DmNPData(
    var name: String,
    var type: DmNPType,
    @Serializable(ANY_SERIALIZER::class) var value: Any? = null
)

typealias type_serializer = (value: Any) -> JsonElement

class ANY_SERIALIZER(override val descriptor: SerialDescriptor = serialDescriptor<Unit>()) : KSerializer<Any?>
{
    companion object {
        val types = hashMapOf<String, type_serializer>(
            Pair(
                "Int", { value -> Json.encodeToJsonElement(value as Int) }
            ),
            Pair(
                "Double", { value -> Json.encodeToJsonElement(value as Double) }
            ))
    }

    override fun deserialize(decoder: Decoder): Any? {
        return null
    }

    @ExperimentalSerializationApi
    override fun serialize(encoder: Encoder, value: Any?) {
        if (value == null) {
            encoder.encodeNull()
            return
        }

        val name = value::class.simpleName!!
        throwCast<Encoder, JsonEncoder>(encoder).encodeJsonElement(buildJsonObject {
            put("type", name)
            put("value", types[name]!!(value))
        })
    }
}

/**
 * @author DomamaN202
 */
@Serializable
enum class DmNPDataType
{
    DmNPData,
    DmNPAData,
    DmNPDataObject
}

/**
 * @author  DomamaN202
 */
@Serializable
enum class DmNPType
{
    NULL,
    OBJECT,
    REFERENCE,

    VAR,
    KMETHOD,
    METHOD,
    CLASS,
    PACKAGE
}

/**
 * @author  DomamaN202
 */
@Serializable
enum class DmNPModifiers
{
    // Access
    PUBLIC,
    PROTECTED,
    PRIVATE,
    //
    STATIC,
    FINAL
}