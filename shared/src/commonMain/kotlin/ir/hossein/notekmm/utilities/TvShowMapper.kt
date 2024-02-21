package ir.hossein.notekmm.utilities

import ir.hossein.notekmm.domain.model.TvShow as TvShowDomain
import ir.hossein.notekmm.data.model.TvShow as TvShowData

class TvShowMapper : Mapper<TvShowData, TvShowDomain>() {

    override fun map(model: TvShowData): TvShowDomain = model.run {
        TvShowDomain(id, name, startDate, status, thumbnail)
    }
}